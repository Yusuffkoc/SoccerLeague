package com.example.soccerleague.api

import com.example.soccerleague.model.Teams
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {


    @GET("json/1/lookup_all_teams.php?id=4339")
    fun getTeams(): Call<Teams>

}