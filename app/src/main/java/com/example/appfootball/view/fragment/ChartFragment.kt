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
import com.example.appfootball.databinding.FragmentChartBinding
import com.example.appfootball.view.adapter.ChartRecyclerViewAdapter
import com.example.appfootball.viewmodel.ChartViewModel


class ChartFragment : Fragment() {

    private lateinit var chartRecyclerViewAdapter: ChartRecyclerViewAdapter
    private lateinit var chartFragmentBinding : FragmentChartBinding

    companion object
    {
        fun getInstance(id : Int) = ChartFragment().apply {
            arguments = bundleOf("id" to id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { bundle ->
            createChartAPI(bundle.getInt("id"))
        }
    }

    private fun createChartAPI(id: Int) {
        val chartViewModel : ChartViewModel by viewModels()
        chartViewModel.chartResult.observe(this, {chartResult ->
            when (chartResult)
            {
                is ChartViewModel.ChartDataResult.ResultOk ->
                {
                    chartRecyclerViewAdapter.lstChart = chartResult.chartList
                }

                is ChartViewModel.ChartDataResult.ResultError ->
                {
                    Toast.makeText(requireContext(), chartResult.chartMessage, Toast.LENGTH_SHORT).show()
                }
            }
            chartFragmentBinding.chartPb.visibility = View.GONE
            chartFragmentBinding.rlChartTitle.visibility = View.VISIBLE
            chartFragmentBinding.rcvChart.visibility = View.VISIBLE
        })
        chartViewModel.makeChartAPICall(id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chartFragmentBinding.rcvChart.apply {
            layoutManager = LinearLayoutManager(context)
            chartRecyclerViewAdapter = ChartRecyclerViewAdapter()
            adapter = chartRecyclerViewAdapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        chartFragmentBinding = FragmentChartBinding.inflate(inflater, container, false)
        return chartFragmentBinding.root
    }

}