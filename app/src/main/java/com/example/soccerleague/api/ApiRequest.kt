package com.example.soccerleague.api

import com.example.soccerleague.model.Teams
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ApiRequest {
    private val services: ApiServices = ApiClient.getClient().create(ApiServices::class.java)

     fun getTeams(listener: ApiRequestListener<Teams>) {
        val call: Call<Teams> = services.getTeams()
        call.enqueue(object : Callback<Teams?> {
            override fun onResponse(call: Call<Teams?>, response: Response<Teams?>) {
                listener.onResponse(response.body()!!)
            }

            override fun onFailure(call: Call<Teams?>, t: Throwable) {
                listener.onFailure(t)
            }
        })
    }

}