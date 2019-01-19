package com.sebastianopighi.bestlyrics.data.repositories.lyrics

import com.sebastianopighi.bestlyrics.domain.models.LyricsEntity
import com.sebastianopighi.bestlyrics.domain.repositories.LyricsRepository
import io.reactivex.Single

class LyricsRepositoryImpl(private val cacheDataStore: LyricsCacheDataStore,
                           private val cloudDataStore: LyricsCloudDataStore
): LyricsRepository {

    override fun getLyrics(trackId: Int): Single<LyricsEntity?> {
        return cacheDataStore.getLyrics(trackId)
            .onErrorResumeNext(cloudDataStore.getLyrics(trackId)
                .doOnSuccess{ it?.let { cacheDataStore.saveLyrics(trackId,it) } })
    }
}