package com.sebastianopighi.bestlyrics.data.networking.lyrics

import com.google.gson.annotations.SerializedName
import com.sebastianopighi.bestlyrics.data.models.LyricsData


data class LyricsBodyResponse(

    @SerializedName("lyrics")
    var lyrics: LyricsData? = null

)