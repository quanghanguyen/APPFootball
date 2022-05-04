package com.example.appfootball.model.chart

import com.google.gson.annotations.SerializedName

data class ChartModel(
    @SerializedName("competition")
    val competition: Competition,
    @SerializedName("filters")
    val filters: Filters,
    @SerializedName("season")
    val season: Season,
    @SerializedName("standings")
    val standings: List<Standing>
)