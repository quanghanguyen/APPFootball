package com.example.appfootball.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appfootball.databinding.ListCompetitionsBinding
import com.example.appfootball.model.competitions.Competition

class CompetitionsRecyclerViewAdapter : RecyclerView.Adapter<CompetitionsRecyclerViewAdapter.MyViewHolder>() {

    private lateinit var mListerner : onItemClickListerner

    interface onItemClickListerner {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListerner(listerner : onItemClickListerner) {
        mListerner = listerner
    }

    var lstCompetitions = ArrayList<Competition>()

    fun getListCompetitions(competitions : ArrayList<Competition>)
    {
        this.lstCompetitions = competitions
    }

    class MyViewHolder(val competitionsBinding: ListCompetitionsBinding, listerner : onItemClickListerner) : RecyclerView.ViewHolder(competitionsBinding.root) {
        fun bind(competitions : Competition)
        {
            with(competitionsBinding)
            {
                tvNameHome.text = competitions.name
                tvstartDate.text = competitions.currentSeason.startDate
                tvendDate.text = competitions.currentSeason.endDate
                tvmatchDay.text = competitions.currentSeason.currentMatchday.toString()

                Glide.with(civHome)
                    .load(competitions.emblemUrl)
                    .into(civHome)
            }
        }
        init {
            competitionsBinding.rlHomeItems.setOnClickListener {
                listerner.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val competitionsBinding = ListCompetitionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder : MyViewHolder = MyViewHolder(competitionsBinding, mListerner)
        return viewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(lstCompetitions[position])
    }

    override fun getItemCount(): Int {
        return lstCompetitions.size
    }
}