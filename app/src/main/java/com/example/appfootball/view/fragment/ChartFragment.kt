package com.example.appfootball.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.appfootball.R


class ChartFragment : Fragment() {


    companion object
    {
        fun getInstance(id:Int) = ChartFragment().apply {
            arguments = bundleOf("id" to id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { bundle ->
            createChartAPI(bundle.getInt("id"))
        }
    }

    private fun createChartAPI(int: Int) {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

}