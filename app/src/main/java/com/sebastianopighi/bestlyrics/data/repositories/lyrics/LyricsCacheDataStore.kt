package com.sebastianopighi.bestlyrics.data.repositories.lyrics

import com.sebastianopighi.bestlyrics.domain.models.LyricsEntity
import com.sebastianopighi.bestlyrics.domain.repositories.LyricsCache
import com.sebastianopighi.bestlyrics.domain.repositories.LyricsDataStore
import io.reactivex.Single

class LyricsCacheDataStore(private val lyricsCache: LyricsCache):
    LyricsDataStore {

    override fun getLyrics(trackId: Int): Single<LyricsEntity?> {
        return lyricsCache.getLyrics(trackId)
    }

    fun saveLyrics(trackId: Int, lyrics: LyricsEntity) {
        lyricsCache.saveLyrics(trackId, lyrics)
    }

}