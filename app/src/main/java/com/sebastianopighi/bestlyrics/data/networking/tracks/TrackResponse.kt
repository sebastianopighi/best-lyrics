package com.sebastianopighi.bestlyrics.data.networking.tracks

import com.google.gson.annotations.SerializedName
import com.sebastianopighi.bestlyrics.data.models.TrackData

data class TrackResponse(

    @SerializedName("track")
    var track: TrackData? = null

)