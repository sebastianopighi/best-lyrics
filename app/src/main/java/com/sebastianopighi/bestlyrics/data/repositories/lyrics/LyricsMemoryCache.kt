package com.sebastianopighi.bestlyrics.data.repositories.lyrics

import com.sebastianopighi.bestlyrics.data.models.LyricsData
import com.sebastianopighi.bestlyrics.data.models.mappers.LyricsDataToEntityMapper
import com.sebastianopighi.bestlyrics.data.models.mappers.LyricsEntityToDataMapper
import com.sebastianopighi.bestlyrics.domain.models.LyricsEntity
import com.sebastianopighi.bestlyrics.domain.repositories.LyricsCache
import io.reactivex.Single

class LyricsMemoryCache: LyricsCache {

    private val lyricsDataMapper = LyricsDataToEntityMapper()
    private val lyricsEntityMapper = LyricsEntityToDataMapper()
    private val lyrics = mutableMapOf<Int, LyricsData?>()

    override fun getLyrics(trackId: Int): Single<LyricsEntity?> {
        return lyrics.get(trackId)?.let {
            Single.just(lyricsDataMapper.mapFrom(it))
        } ?: Single.error(Throwable("No cached lyrics found"))
    }

    override fun saveLyrics(trackId: Int, lyrics: LyricsEntity) {
        this.lyrics.put(trackId, lyricsEntityMapper.mapFrom(lyrics))
    }

}