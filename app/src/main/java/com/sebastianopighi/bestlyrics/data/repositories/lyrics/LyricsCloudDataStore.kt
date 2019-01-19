package com.sebastianopighi.bestlyrics.data.repositories.lyrics

import com.sebastianopighi.bestlyrics.data.models.mappers.LyricsDataToEntityMapper
import com.sebastianopighi.bestlyrics.data.networking.Api
import com.sebastianopighi.bestlyrics.domain.models.LyricsEntity
import com.sebastianopighi.bestlyrics.domain.repositories.LyricsDataStore
import io.reactivex.Single

class LyricsCloudDataStore(private val api: Api): LyricsDataStore {

    private val lyricsDataMapper = LyricsDataToEntityMapper()

    override fun getLyrics(trackId: Int): Single<LyricsEntity?> {
        return api.getLyrics(trackId)
            .map { lyricsDataMapper.mapFrom(it.message?.body?.lyrics)}
    }

}