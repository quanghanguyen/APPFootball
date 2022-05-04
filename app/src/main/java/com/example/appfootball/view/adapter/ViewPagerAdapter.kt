package com.example.appfootball.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.appfootball.view.fragment.ChartFragment
import com.example.appfootball.view.fragment.TeamsFragment

class ViewPagerAdapter (fm : FragmentManager, lifecycler : Lifecycle, val id : Int) : FragmentStateAdapter(fm, lifecycler)  {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position)
        {
            0 ->
            {
                TeamsFragment.getInstance(id)
            }

            1 ->
            {
                ChartFragment.getInstance(id)
            }

            else ->
            {
                Fragment()
            }


        }
    }
}