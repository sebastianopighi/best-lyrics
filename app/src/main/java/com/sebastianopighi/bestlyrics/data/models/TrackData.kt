package com.sebastianopighi.bestlyrics.data.models

import com.google.gson.annotations.SerializedName

data class TrackData(

    @SerializedName("track_id")
    var track_id: Int = 0,

    @SerializedName("track_name")
    var track_name: String = "",

    @SerializedName("track_rating")
    var track_rating: Int = 0,

    @SerializedName("commontrack_id")
    var commontrack_id: Int = 0,

    @SerializedName("instrumental")
    var instrumental: Int = 0,

    @SerializedName("explicit")
    var explicit: Int = 0,

    @SerializedName("has_lyrics")
    var has_lyrics: Int = 0,

    @SerializedName("has_subtitles")
    var has_subtitles: Int = 0,

    @SerializedName("has_richsync")
    var has_richsync: Int = 0,

    @SerializedName("num_favourite")
    var num_favourite: Int = 0,

    @SerializedName("album_id")
    var album_id: Int = 0,

    @SerializedName("album_name")
    var album_name: String = "",

    @SerializedName("artist_id")
    var artist_id: Int = 0,

    @SerializedName("artist_name")
    var artist_name: String = "",

    @SerializedName("track_share_url")
    var track_share_url: String = "",

    @SerializedName("track_edit_url")
    var track_edit_url: String = "",

    @SerializedName("restricted")
    var restricted: Int = 0,

    @SerializedName("updated_time")
    var updated_time: String = ""
)
