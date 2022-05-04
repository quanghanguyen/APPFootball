package com.example.appfootball.model.teams

import com.google.gson.annotations.SerializedName

data class AreaX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)