package com.example.soccerleague.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.soccerleague.R
import com.example.soccerleague.databinding.FragmentFixtureBinding
import com.example.soccerleague.databinding.FragmentTeamListBinding
import com.example.soccerleague.model.Teams
import com.example.soccerleague.viewModel.FixtureViewModel
import com.example.soccerleague.viewModel.TeamListViewModel
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.absoluteValue


class FixtureFragment : Fragment() {

    companion object {
        fun newInstance() = FixtureFragment()
    }


    private var _binding: FragmentFixtureBinding? = null
    var layoutManager = LinearLayoutManager(this.context)
    private lateinit var allFixtures: MutableList<Array<Array<String>>>
    var teamList = mutableListOf<String>()

    private val binding get() = _binding!!
    private lateinit var viewModel: FixtureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFixtureBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FixtureViewModel::class.java)
        //var viewModelTeamList = ViewModelProvider(requireActivity()).get(TeamListViewModel::class.java)

        viewModel.getTeams()

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.baseRecyclerView)
        binding.baseRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.baseRecyclerView.adapter=viewModel.baseAdapter



        viewModel.teamResponsee?.observe(viewLifecycleOwner, object : Observer<Teams?> {
            override fun onChanged(t: Teams?) {
                if (t != null) {
                    for (i in t.teams.indices) {
                        viewModel.teamList.add(t.teams[i].strTeam)
                    }
                    getFixture(viewModel.teamList)
                }
            }
        })

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    private fun getFixture(teamList: List<String>) {
        var baseTeam : String? = null
        val teams = teamList.toMutableList()
        if (teams.size % 2 != 0){
            teams.add("Pass")
        }
        val shuffTeams = teams.shuffled().toMutableList()

        baseTeam = shuffTeams[0]
        shuffTeams.removeAt(0)

        //Butun fixturleri cektik cift sayi icin
        val fixtures = drawFixture(shuffTeams,baseTeam)

        viewModel.baseAdapter.fixtureList = fixtures
        viewModel.baseAdapter.notifyDataSetChanged()

        println("--------- all fixtures -----------")
        for (i in 0 until teams.size-1){
            for (j in 0 until teams.size/2){
                println(fixtures[i][j].toList())
            }
        }
        println("Fixture Size: ${fixtures[0].size}")
    }

    fun drawFixture(shuffTeams: List<String>, baseTeam: String): MutableList<Array<Array<String>>> {

        var array = shuffTeams
        allFixtures = mutableListOf()

        println("------------ base array -----------")
        println(array.toList())

        for (i in array.indices){

            val fixtureMatrix = arrayOf(arrayOf(baseTeam,array[0]))
            val newMatrix = fixtureMatrix.toMutableList()

            for (j in 1 until ((array.size/2)+1)){
                newMatrix.add(j, arrayOf(array[j],array[array.lastIndex-j+1]))
            }
            allFixtures.add(i,newMatrix.toTypedArray())
            newMatrix.clear()
            array = getNewArray(array)

            println("------------ new array -----------")
            println(array.toList())
        }

        return allFixtures
    }

    fun getNewArray(array: List<String>): List<String> {

        val newArray = array.toMutableList()

        newArray.add(0,array[array.lastIndex])
        newArray.removeAt(newArray.lastIndex)

        return newArray.toList()

    }

}