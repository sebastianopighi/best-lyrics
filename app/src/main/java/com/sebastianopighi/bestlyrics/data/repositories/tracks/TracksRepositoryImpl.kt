package com.sebastianopighi.bestlyrics.data.repositories.tracks

import com.sebastianopighi.bestlyrics.domain.models.TrackEntity
import com.sebastianopighi.bestlyrics.domain.repositories.TracksRepository
import io.reactivex.Single

class TracksRepositoryImpl(private val cacheDataStore: TracksCacheDataStore,
                           private val cloudDataStore: TracksCloudDataStore
): TracksRepository {

    override fun getTracks(page: Int): Single<List<TrackEntity>> {
        return cloudDataStore.getTracks(page)
            .map { it.filterNotNull() }
            .doOnSuccess { it?.let { cacheDataStore.saveTracks(it) } }
    }

    override fun getTrack(trackId: Int): Single<TrackEntity> {
        return cacheDataStore.getTrack(trackId)
    }
}