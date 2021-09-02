package com.example.soccerleague.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soccerleague.adapter.FixtureBaseAdapter
import com.example.soccerleague.api.ApiRequest
import com.example.soccerleague.api.ApiRequestListener
import com.example.soccerleague.model.Teams

class FixtureViewModel : ViewModel() {

    var teamResponsee: MutableLiveData<Teams>? = MutableLiveData<Teams>()
    val baseAdapter = FixtureBaseAdapter(arrayListOf())
    var teamList = arrayListOf<String>()

    fun getTeams() {
        ApiRequest.getTeams(object : ApiRequestListener<Teams> {
            override fun onResponse(response: Teams) {
                teamResponsee?.value = response
            }
            override fun onFailure(t: Throwable?) {
                Log.d("teams", "getTeams:onFailure:called")
            }

        })
    }
}