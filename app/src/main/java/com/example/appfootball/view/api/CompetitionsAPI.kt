package com.example.appfootball.view.api

import com.example.appfootball.model.competitions.CompetitionsModel
import retrofit2.Call
import retrofit2.http.GET

interface CompetitionsAPI {

    // https://api.football-data.org/v2/competitions

    @GET("v2/competitions")
    fun getCompetition() : Call<CompetitionsModel>

}