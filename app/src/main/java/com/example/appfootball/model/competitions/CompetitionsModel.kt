package com.example.appfootball.model.competitions

import com.google.gson.annotations.SerializedName

data class CompetitionsModel(
    @SerializedName("competitions")
    val competitions: List<Competition>,
    @SerializedName("count")
    val count: Int,
    @SerializedName("filters")
    val filters: Filters
)