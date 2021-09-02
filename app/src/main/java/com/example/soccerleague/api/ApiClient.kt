package com.example.soccerleague.api

import com.example.soccerleague.util.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null)
            retrofit =
                Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(
                    GsonConverterFactory.create()).build()

        return retrofit as Retrofit
    }

}
