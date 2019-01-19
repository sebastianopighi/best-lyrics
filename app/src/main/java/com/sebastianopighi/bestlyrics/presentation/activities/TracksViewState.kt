package com.sebastianopighi.bestlyrics.presentation.activities

import com.sebastianopighi.bestlyrics.presentation.models.Track

data class TracksViewState(
    var showLoading: Boolean = true,
    var addTracks: List<Track>? = null,
    var tracks: MutableList<Track>? = null
)