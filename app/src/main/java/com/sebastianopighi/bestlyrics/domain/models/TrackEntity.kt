package com.sebastianopighi.bestlyrics.domain.models

data class TrackEntity(
    var track_id: Int = 0,
    var track_name: String = "",
    var track_rating: Int = 0,
    var explicit: Int = 0,
    var has_lyrics: Int = 0,
    var has_subtitles: Int = 0,
    var num_favourite: Int = 0,
    var album_id: Int = 0,
    var album_name: String = "",
    var album_cover_url: String = "",
    var album_release_date: String = "",
    var artist_id: Int = 0,
    var artist_name: String = "",
    var track_share_url: String = ""
)