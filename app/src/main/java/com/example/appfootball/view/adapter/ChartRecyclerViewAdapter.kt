package com.example.appfootball.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appfootball.databinding.ListChartBinding
import com.example.appfootball.model.chart.Table

class ChartRecyclerViewAdapter : RecyclerView.Adapter<ChartRecyclerViewAdapter.MyViewHolder>() {

    var lstChart = ArrayList<Table>()

    fun getListChart(chartData : ArrayList<Table>)
    {
        this.lstChart = chartData
    }

    class MyViewHolder(val chartBinding : ListChartBinding) : RecyclerView.ViewHolder(chartBinding.root) {
        fun bind(chartData: Table)
        {
            with(chartBinding)
            {
                tvPosition.text = chartData.position.toString()
                tvNameTeam.text = chartData.team.name
                tvW.text = chartData.won.toString()
                tvL.text = chartData.lost.toString()
                tvD.text = chartData.draw.toString()
                tvP.text = chartData.points.toString()

                Glide.with(civTeamIcon)
                    .load(chartData.team.crestUrl)
                    .into(civTeamIcon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val chartBinding = ListChartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder : MyViewHolder = MyViewHolder(chartBinding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(lstChart[position])
    }

    override fun getItemCount(): Int {
        return lstChart.size
    }
}