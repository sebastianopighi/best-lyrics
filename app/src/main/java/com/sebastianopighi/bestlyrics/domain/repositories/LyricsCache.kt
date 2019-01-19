package com.sebastianopighi.bestlyrics.domain.repositories

import com.sebastianopighi.bestlyrics.domain.models.LyricsEntity
import io.reactivex.Single

interface LyricsCache {
    fun getLyrics(trackId: Int): Single<LyricsEntity?>
    fun saveLyrics(trackId: Int, lyrics: LyricsEntity)
}