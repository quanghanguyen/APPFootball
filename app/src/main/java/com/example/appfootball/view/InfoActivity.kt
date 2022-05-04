package com.example.appfootball.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appfootball.R
import com.example.appfootball.databinding.ActivityInfoBinding
import com.example.appfootball.view.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class InfoActivity : AppCompatActivity() {

    private lateinit var infoBinding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val id = intent?.extras?.getInt("id") ?: 0
        val id = intent?.getIntExtra("id", 0)
        infoBinding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(infoBinding.root)
        if (id != null) {
            setViewPager(id)
        }
    }

    private fun setViewPager(id : Int) {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle, id)
        infoBinding.vpInfo.adapter = viewPagerAdapter

        TabLayoutMediator(infoBinding.tlInfo, infoBinding.vpInfo) {tab, position ->
            when (position)
            {
                0 ->
                {
                    tab.text = "TEAMS"
                }

                1 ->
                {
                    tab.setIcon(R.drawable.ic_baseline_insert_chart_24)
                }
            }
        }.attach()
    }
}