package com.rizalfahrudin.moviecatalogue.core.data

import com.rizalfahrudin.moviecatalogue.core.data.source.remote.network.ApiResponse
import com.rizalfahrudin.moviecatalogue.core.utils.AppExecutor
import com.rizalfahrudin.moviecatalogue.core.vo.Resource
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutor: AppExecutor) {


    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        if (showFetchData(dbSource)) {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }

                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error<ResultType>(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { Resource.Success(it) })
        }
    }

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun showFetchData(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    private fun onFetchFailed() {}


    fun asFlow(): Flow<Resource<ResultType>> = result
}