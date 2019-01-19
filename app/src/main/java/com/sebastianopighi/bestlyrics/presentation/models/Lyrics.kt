package com.sebastianopighi.bestlyrics.presentation.models

data class Lyrics(
    var lyrics_id: Int = 0,
    var explicit: Int = 0,
    var lyrics_body: String = "",
    var pixel_tracking_url: String = "",
    var lyrics_copyright: String = "",
    var updated_time: String = ""
)