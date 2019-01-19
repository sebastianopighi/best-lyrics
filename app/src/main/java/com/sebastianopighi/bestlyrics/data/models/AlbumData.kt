package com.sebastianopighi.bestlyrics.data.models

import com.google.gson.annotations.SerializedName

data class AlbumData(

    @SerializedName("album_id")
    var album_id: Int = 0,

    @SerializedName("album_mbid")
    var album_mbid: String = "",

    @SerializedName("album_name")
    var album_name: String = "",

    @SerializedName("album_rating")
    var album_rating: Int = 0,

    @SerializedName("album_track_count")
    var album_track_count: Int = 0,

    @SerializedName("album_release_date")
    var album_release_date: String = "",

    @SerializedName("album_release_type")
    var album_release_type: String = "",

    @SerializedName("artist_id")
    var artist_id: Int = 0,

    @SerializedName("artist_name")
    var artist_name: String = "",

    @SerializedName("album_pline")
    var album_pline: String = "",

    @SerializedName("album_copyright")
    var album_copyright: String = "",

    @SerializedName("album_label")
    var album_label: String = "",

    @SerializedName("updated_time")
    var updated_time: String = "",

    @SerializedName("album_coverart_100x100")
    var album_coverart: String = ""
)