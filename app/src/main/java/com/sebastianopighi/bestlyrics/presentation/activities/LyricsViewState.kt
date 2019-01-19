package com.sebastianopighi.bestlyrics.presentation.activities

import com.sebastianopighi.bestlyrics.presentation.models.Lyrics
import com.sebastianopighi.bestlyrics.presentation.models.Track

data class LyricsViewState(
    var showLoading: Boolean = true,
    var track: Track? = null,
    var lyrics: Lyrics? = null
)