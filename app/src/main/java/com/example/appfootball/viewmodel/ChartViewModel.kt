package com.example.appfootball.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appfootball.model.chart.ChartModel
import com.example.appfootball.model.chart.Table
import com.example.appfootball.model.chart.Team
import com.example.appfootball.view.api.ChartAPI
import com.example.appfootball.view.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChartViewModel : ViewModel() {

    var chartResult = MutableLiveData<ChartDataResult>()

    private lateinit var chartCall : Call<ChartModel>

    sealed class ChartDataResult
    {
        class ResultOk(val chartList : ArrayList<Table>) : ChartDataResult()
        class ResultError(val chartMessage : String) : ChartDataResult()
    }

    fun makeChartAPICall(id : Int)
    {
        val chartData = RetrofitInstance.getRetrofitInstance().create(ChartAPI::class.java)
        chartCall = chartData.getChartDetail(id)

        chartCall.enqueue(object : Callback<ChartModel?> {
            override fun onResponse(call: Call<ChartModel?>, response: Response<ChartModel?>) {
                if (response.isSuccessful)
                {
                    val listChartStandings = response.body()!!.standings
                    val listChartResult = listChartStandings[0].table
                    chartResult.postValue(ChartDataResult.ResultOk(ArrayList(listChartResult)))
                } else
                {
                    chartResult.postValue(ChartDataResult.ResultError("Error"))
                }
            }

            override fun onFailure(call: Call<ChartModel?>, t: Throwable) {
                chartResult.postValue(ChartDataResult.ResultError("Error"))
            }
        })
    }
}