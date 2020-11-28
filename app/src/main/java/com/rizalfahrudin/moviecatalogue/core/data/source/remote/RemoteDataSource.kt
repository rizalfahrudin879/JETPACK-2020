package com.rizalfahrudin.moviecatalogue.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.network.ApiResponse
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.network.ApiService
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.response.MovieEntityResponse
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.response.TvEntityResponse
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.response.TvResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(
    private val apiService: ApiService
){

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(
            apiService: ApiService
        ): RemoteDataSource =
            instance
                ?: synchronized(this) {
                    instance
                        ?: RemoteDataSource(apiService)
                }
    }

    fun getMovie(): LiveData<ApiResponse<MovieResponse>> {
        val resultsMovie = MutableLiveData<ApiResponse<MovieResponse>>()

        val client = apiService.getMovie()
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val dataArray = response.body()
                resultsMovie.value =
                    if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                resultsMovie.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }

        })
        return resultsMovie
    }

    fun getTv(): LiveData<ApiResponse<TvResponse>> {
        val resultsTv = MutableLiveData<ApiResponse<TvResponse>>()

        val client = apiService.getTv()
        client.enqueue(object : Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                val dataArray = response.body()
                resultsTv.value =
                    if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                resultsTv.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }

        })
        return resultsTv
    }

    fun getMovieById(id: Int): LiveData<ApiResponse<MovieEntityResponse>> {
        val resultMovie = MutableLiveData<ApiResponse<MovieEntityResponse>>()

        val client = apiService.getMovieById(id)
        client.enqueue(object : Callback<MovieEntityResponse> {
            override fun onResponse(
                call: Call<MovieEntityResponse>,
                response: Response<MovieEntityResponse>
            ) {
                val dataArray = response.body()
                resultMovie.value =
                    if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<MovieEntityResponse>, t: Throwable) {
                resultMovie.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }

        })
        return resultMovie
    }

    fun getTvById(id: Int): LiveData<ApiResponse<TvEntityResponse>> {
        val resultTv = MutableLiveData<ApiResponse<TvEntityResponse>>()

        val client = apiService.getTvById(id)
        client.enqueue(object : Callback<TvEntityResponse> {
            override fun onResponse(
                call: Call<TvEntityResponse>,
                response: Response<TvEntityResponse>
            ) {
                val dataArray = response.body()
                resultTv.value =
                    if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<TvEntityResponse>, t: Throwable) {
                resultTv.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }

        })
        return resultTv
    }

}
