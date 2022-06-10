package com.geco.challangech8

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.geco.challangech8.model.Movie
import com.geco.challangech8.model.MovieResponse
import com.geco.challangech8.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    var movieListResponse:List<Movie> by mutableStateOf(listOf())


    fun getMovieData() {
        ApiClient.getInstance().getMovie()
            .enqueue(object: Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                }
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    return when {
                        response.isSuccessful -> {
                            movieListResponse = response.body()!!.movies
                        }
                        else -> {}
                    }

                }
            })
    }
}