package com.sebastianopighi.bestlyrics.presentation.models

data class Album(
    var album_id: Int = 0,
    var album_name: String = "",
    var album_rating: Int = 0,
    var album_track_count: Int = 0,
    var album_release_date: String = "",
    var album_label: String = "",
    var album_coverart: String = ""
)