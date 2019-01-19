package com.sebastianopighi.bestlyrics.data.models

import com.google.gson.annotations.SerializedName

data class LyricsData(

    @SerializedName("lyrics_id")
    var lyrics_id: Int = 0,

    @SerializedName("explicit")
    var explicit: Int = 0,

    @SerializedName("lyrics_body")
    var lyrics_body: String = "",

    @SerializedName("script_tracking_url")
    var script_tracking_url: String = "",

    @SerializedName("pixel_tracking_url")
    var pixel_tracking_url: String = "",

    @SerializedName("lyrics_copyright")
    var lyrics_copyright: String = "",

    @SerializedName("updated_time")
    var updated_time: String = ""

)