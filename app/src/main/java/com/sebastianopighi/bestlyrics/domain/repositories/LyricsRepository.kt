package com.sebastianopighi.bestlyrics.domain.repositories

import com.sebastianopighi.bestlyrics.domain.models.LyricsEntity
import io.reactivex.Single

interface LyricsRepository {
    fun getLyrics(trackId: Int): Single<LyricsEntity?>
}