package com.example.soccerleague.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soccerleague.adapter.TeamsAdapter
import com.example.soccerleague.api.ApiRequest
import com.example.soccerleague.api.ApiRequestListener
import com.example.soccerleague.model.TeamResponse
import com.example.soccerleague.model.Teams

class TeamListViewModel : ViewModel() {
    var teamResponse: MutableLiveData<Teams>? = MutableLiveData<Teams>()
    var teamsAdapter = TeamsAdapter()

    fun getTeams() {
        ApiRequest.getTeams(object : ApiRequestListener<Teams> {
            override fun onResponse(response: Teams) {
                teamResponse?.value = response
            }

            override fun onFailure(t: Throwable?) {
                Log.d("teams", "getTeams:onFailure:called")
            }

        })
    }
}