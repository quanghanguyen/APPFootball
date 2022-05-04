package com.example.appfootball.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appfootball.R
import com.example.appfootball.databinding.FragmentTeamsBinding
import com.example.appfootball.model.teams.Team
import com.example.appfootball.view.adapter.TeamsRecyclerViewAdapter
import com.example.appfootball.viewmodel.TeamsViewModel


class TeamsFragment : Fragment() {

    private lateinit var teamsRecyclerViewAdapter: TeamsRecyclerViewAdapter
    private lateinit var fragmentTeamsBinding: FragmentTeamsBinding

    companion object
    {
        fun getInstance(id : Int) = TeamsFragment().apply {
            arguments = bundleOf("id" to id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { bundle ->
            createTeamsAPI(bundle.getInt("id"))
        }
    }

    private fun createTeamsAPI(id: Int) {
        val teamsViewModel : TeamsViewModel by viewModels()
        teamsViewModel.teamsResult.observe(this, {teamsResult ->
            when (teamsResult)
            {
                is TeamsViewModel.TeamsDataResult.ResultOk ->
                {
                    teamsRecyclerViewAdapter.lstTeams = teamsResult.teamsList
                }

                is TeamsViewModel.TeamsDataResult.ResultError ->
                {
                    Toast.makeText(requireContext(), teamsResult.teamsMessage, Toast.LENGTH_SHORT).show()
                }
            }
            fragmentTeamsBinding.teamsPb.visibility = View.GONE
            fragmentTeamsBinding.rcvTeams.visibility = View.VISIBLE
        })
        teamsViewModel.makeTeamsAPICall(id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentTeamsBinding.rcvTeams.apply {
            layoutManager = LinearLayoutManager(context)
            teamsRecyclerViewAdapter = TeamsRecyclerViewAdapter()
            adapter = teamsRecyclerViewAdapter

            // Item Click
            teamsRecyclerViewAdapter.setOnTeamsItemClickListerner(object : TeamsRecyclerViewAdapter.onTeamsItemClickLIsterner {
                override fun onTeamsItemClick(teamsData: Team) {
                    Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentTeamsBinding = FragmentTeamsBinding.inflate(inflater, container, false)
        return fragmentTeamsBinding.root
    }
}