package com.sebastianopighi.bestlyrics.data.networking.tracks

import com.google.gson.annotations.SerializedName

data class TracksBodyResponse(

    @SerializedName("track_list")
    var track_list: List<TrackResponse>? = null

    )