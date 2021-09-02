package com.example.soccerleague.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccerleague.databinding.FragmentTeamListBinding
import com.example.soccerleague.model.Teams
import com.example.soccerleague.viewModel.TeamListViewModel
import androidx.navigation.fragment.findNavController
import android.preference.PreferenceManager

import android.content.SharedPreferences
import android.os.Parcelable


class TeamListFragment : Fragment() {

    companion object {
        fun newInstance() = TeamListFragment()
    }

    private var _binding: FragmentTeamListBinding? = null
    var layoutManager = LinearLayoutManager(this.context)


    private val binding get() = _binding!!

    private lateinit var viewModel: TeamListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(TeamListViewModel::class.java)
        viewModel.getTeams()

        layoutManager = GridLayoutManager(this.context, 2)
        binding.rvTeam.layoutManager = layoutManager
        binding.rvTeam.adapter = viewModel.teamsAdapter

        viewModel.teamResponse?.observe(viewLifecycleOwner, object : Observer<Teams?> {
            override fun onChanged(t: Teams?) {
                if (t != null) {
                    viewModel.teamsAdapter.setData(t.teams, requireContext())
                }
            }
        })

        binding.btnDrawFixture.setOnClickListener {
            val action = TeamListFragmentDirections.actionTeamListFragmentToFixtureFragment()
            findNavController().navigate(action)
        }
    }

}