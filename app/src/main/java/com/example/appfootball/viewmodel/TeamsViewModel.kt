package com.example.appfootball.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appfootball.model.teams.Team
import com.example.appfootball.model.teams.TeamsModel
import com.example.appfootball.view.api.RetrofitInstance
import com.example.appfootball.view.api.TeamsAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamsViewModel : ViewModel() {

    var teamsResult = MutableLiveData<TeamsDataResult>()

    lateinit var call : Call<TeamsModel>

    sealed class TeamsDataResult
    {
        class ResultOk(val  teamsList: ArrayList<Team>) : TeamsDataResult()
        class ResultError(val teamsMessage : String) : TeamsDataResult()
    }

    fun makeTeamsAPICall(id : Int)
    {
        val teamsData = RetrofitInstance.getRetrofitInstance().create(TeamsAPI::class.java)
        call = teamsData.getTeamDetail(id)

        call.enqueue(object : Callback<TeamsModel?> {
            override fun onResponse(call: Call<TeamsModel?>, response: Response<TeamsModel?>) {
                if (response.isSuccessful)
                {
                    val listTeamsResult = response.body()!!.teams
                    teamsResult.postValue(TeamsDataResult.ResultOk(ArrayList(listTeamsResult)))
                } else
                {
                    teamsResult.postValue(TeamsDataResult.ResultError("error"))
                }
            }

            override fun onFailure(call: Call<TeamsModel?>, t: Throwable) {
                teamsResult.postValue(TeamsDataResult.ResultError("error"))
            }
        })
    }
}