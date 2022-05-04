package com.example.appfootball.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appfootball.model.competitions.Competition
import com.example.appfootball.model.competitions.CompetitionsModel
import com.example.appfootball.view.api.CompetitionsAPI
import com.example.appfootball.view.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompetitionsViewModel : ViewModel() {

    var result = MutableLiveData<DataResult>()

    sealed class DataResult
    {
        class ResultOk(val list : ArrayList<Competition>) : DataResult()
        class ResultError(val message : String) : DataResult()
    }

    fun makeCompetitionsAPICall()
    {
        val competitions = RetrofitInstance.getRetrofitInstance().create(CompetitionsAPI::class.java)
        val competitionsCall = competitions.getCompetition()

        competitionsCall.enqueue(object : Callback<CompetitionsModel?> {
            override fun onResponse(
                call: Call<CompetitionsModel?>,
                response: Response<CompetitionsModel?>
            ) {
                if (response.isSuccessful)
                {
                    response.body()?.let { modelCompetitions ->
                        val listResult = modelCompetitions.competitions.filter {
                            it.id == 2021 || it.id == 2002 || it.id == 2015 || it.id == 2019
                        }
                        result.postValue(DataResult.ResultOk(ArrayList(listResult)))
                    }
                } else
                {
                    result.postValue(DataResult.ResultError("Error"))
                }
            }

            override fun onFailure(call: Call<CompetitionsModel?>, t: Throwable) {
                result.postValue(DataResult.ResultError("Error"))
            }
        })
    }

}