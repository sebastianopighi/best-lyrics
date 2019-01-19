package com.sebastianopighi.bestlyrics.presentation.models

data class Track(
    var track_id: Int = 0,
    var track_name: String = "",
    var album_id: Int = 0,
    var album_name: String = "",
    var album_cover_url: String = "",
    var album_release_date: String = "",
    var artist_id: Int = 0,
    var artist_name: String = ""
)