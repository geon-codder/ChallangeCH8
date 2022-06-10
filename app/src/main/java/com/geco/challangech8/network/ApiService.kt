package com.geco.challangech8.network

import com.geco.challangech8.API_KEY
import com.geco.challangech8.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getMovie(
        @Query("api_key") api_key: String = API_KEY.apiKey,
    ): Call<MovieResponse>

}

