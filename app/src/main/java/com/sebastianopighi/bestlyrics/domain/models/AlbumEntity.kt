package com.sebastianopighi.bestlyrics.domain.models

data class AlbumEntity(
    var album_id: Int = 0,
    var album_name: String = "",
    var album_rating: Int = 0,
    var album_track_count: Int = 0,
    var album_release_date: String = "",
    var artist_id: Int = 0,
    var artist_name: String = "",
    var album_copyright: String = "",
    var album_label: String = "",
    var album_coverart: String = ""
)