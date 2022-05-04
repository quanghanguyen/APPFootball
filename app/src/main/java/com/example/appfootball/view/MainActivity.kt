package com.example.appfootball.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appfootball.R
import com.example.appfootball.databinding.ActivityMainBinding
import com.example.appfootball.view.adapter.CompetitionsRecyclerViewAdapter
import com.example.appfootball.viewmodel.CompetitionsViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var competitionsRecyclerViewAdapter: CompetitionsRecyclerViewAdapter
    private lateinit var mainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initData()

        initRecyclerView()

    }

    private fun initRecyclerView() {
        mainBinding.rcvHome.apply {
            layoutManager = LinearLayoutManager(context)
            competitionsRecyclerViewAdapter = CompetitionsRecyclerViewAdapter()
            adapter = competitionsRecyclerViewAdapter

            competitionsRecyclerViewAdapter.setOnItemClickListerner(object : CompetitionsRecyclerViewAdapter.onItemClickListerner {
                override fun onItemClick(position: Int) {
                    when (position)
                    {
                        0, 1, 2, 3 -> intent = Intent(this@MainActivity, InfoActivity::class.java).apply {
                            intent.putExtra("id", competitionsRecyclerViewAdapter.lstCompetitions[position].id)
                        }
                        else -> Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                    }
                    startActivity(intent)
                }
            })
        }
    }

    private fun initData() {
        val competitionsViewModel : CompetitionsViewModel by viewModels()
        competitionsViewModel.result.observe(this, { result ->
            when (result)
            {
                is CompetitionsViewModel.DataResult.ResultOk ->
                {
                    competitionsRecyclerViewAdapter.lstCompetitions = result.list
                }

                is CompetitionsViewModel.DataResult.ResultError ->
                {
                    Toast.makeText(baseContext, result.message, Toast.LENGTH_SHORT).show()
                }
            }
            mainBinding.progressBar.visibility = View.GONE
            mainBinding.tvHome.visibility = View.VISIBLE
            mainBinding.rcvHome.visibility = View.VISIBLE
        })
        competitionsViewModel.makeCompetitionsAPICall()
    }
}