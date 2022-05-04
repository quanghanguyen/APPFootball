package com.example.appfootball.model.teams

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AreaX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
) : Parcelable
{
    override fun toString(): String {
        return "AreaX(id=$id, name='$name')"
    }
}