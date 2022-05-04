package com.example.appfootball.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appfootball.R
import com.example.appfootball.databinding.ActivityDetailsBinding
import com.example.appfootball.model.teams.Team

class DetailsActivity : AppCompatActivity() {

    private lateinit var detailsBinding: ActivityDetailsBinding

    companion object
    {
        const val KEY_DATA = "teams_data"
        fun startDetails(context: Context, data : Team)
        {
            context.startActivity(Intent(context, DetailsActivity::class.java).apply {
                putExtra(KEY_DATA, data)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailsBinding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(detailsBinding.root)
        intent?.let { bundle ->
            val teams = bundle.getParcelableExtra<Team>(KEY_DATA)
            detailsBinding.tvDetails.text = teams!!.name
        }




    }
}