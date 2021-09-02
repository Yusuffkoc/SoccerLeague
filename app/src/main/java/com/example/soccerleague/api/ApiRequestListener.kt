package com.example.soccerleague.api

interface ApiRequestListener<T> {
    fun onResponse(response: T)
    fun onFailure(t: Throwable?)
}
