package com.rizalfahrudin.moviecatalogue.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.ApiResponse
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.StatusResponse
import com.rizalfahrudin.moviecatalogue.core.utils.AppExecutor
import com.rizalfahrudin.moviecatalogue.core.vo.Resource

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutor: AppExecutor) {
    private val result = MediatorLiveData<Resource<ResultType>>()
    init {
        result.value = Resource.loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource) {
            result.removeSource(dbSource)
            if (showFetchData(it)) fetchFromNetwork(dbSource)
            else result.addSource(dbSource) { newData ->
                result.value = Resource.success(newData)
            }
        }
    }

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun showFetchData(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun onFetchFailed() {}

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) {
            result.value = Resource.loading(it)
        }

        result.addSource(apiResponse) {response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when(response.status) {
                StatusResponse.SUCCESS -> {
                    mExecutor.diskIO().execute {
                        saveCallResult(response.body)
                        mExecutor.mainThread().execute {
                            result.addSource(loadFromDB()) { newData ->
                                result.value = Resource.success(newData)
                            }
                        }
                    }
                }
                StatusResponse.EMPTY -> {
                    mExecutor.mainThread().execute {
                        result.addSource(loadFromDB()) { newData ->
                            result.value = Resource.success(newData)
                        }
                    }
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.error(newData, response.message)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}