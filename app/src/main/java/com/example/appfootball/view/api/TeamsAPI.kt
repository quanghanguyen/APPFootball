package com.example.appfootball.view.api

import com.example.appfootball.model.teams.TeamsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TeamsAPI {

    @GET("v2/competitions/{id}/teams")
    fun getTeamDetail(@Path("id") id:Int) : Call<TeamsModel>

}