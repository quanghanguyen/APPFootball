package com.example.appfootball.view.api

import com.example.appfootball.model.chart.ChartModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ChartAPI {

    // https://api.football-data.org/v2/competitions/2021/standings?standingType=HOME

    @GET("v2/competitions/{id}/standings?standingType=HOME")
    fun getChartDetail(@Path("id") id : Int) : Call<ChartModel>

}