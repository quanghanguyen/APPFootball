package com.example.appfootball.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appfootball.databinding.ListTeamsBinding
import com.example.appfootball.model.teams.Team

class TeamsRecyclerViewAdapter : RecyclerView.Adapter<TeamsRecyclerViewAdapter.MyViewHolder>() {

    private lateinit var mListerner : onTeamsItemClickLIsterner

    interface onTeamsItemClickLIsterner {
        fun onTeamsItemClick(teamsData: Team)
    }

    fun setOnTeamsItemClickListerner(listerner : onTeamsItemClickLIsterner) {
        mListerner = listerner
    }

    var lstTeams = ArrayList<Team>()

    fun setListTeams(teamsData : ArrayList<Team>) {
        this.lstTeams = teamsData
    }

    fun getListTeam() = lstTeams

    class MyViewHolder(val teamsBinding : ListTeamsBinding, val listerner : onTeamsItemClickLIsterner)
        : RecyclerView.ViewHolder(teamsBinding.root)
    {
        fun bind(teamsData: Team)
        {
            with(teamsBinding)
            {
                tvTeams.text = teamsData.name

                Glide.with(civTeams)
                    .load(teamsData.crestUrl)
                    .into(civTeams)

                teamsBinding.rlItemTeams.setOnClickListener {
                    listerner.onTeamsItemClick(teamsData)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val teamsBinding = ListTeamsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder : MyViewHolder = MyViewHolder(teamsBinding, mListerner)

        return viewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(lstTeams[position])
    }

    override fun getItemCount(): Int {
        return lstTeams.size
    }
}