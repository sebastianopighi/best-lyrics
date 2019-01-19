package com.sebastianopighi.bestlyrics.data.repositories.tracks

import com.sebastianopighi.bestlyrics.domain.models.TrackEntity
import com.sebastianopighi.bestlyrics.domain.repositories.TracksCache
import com.sebastianopighi.bestlyrics.domain.repositories.TracksDataStore
import io.reactivex.Single

class TracksCacheDataStore(private val tracksCache: TracksCache):
    TracksDataStore {

    override fun getTracks(page: Int): Single<List<TrackEntity?>> {
        return tracksCache.getAllTracks()
    }

    fun getTrack(trackId: Int): Single<TrackEntity> {
        return tracksCache.getTrack(trackId)
    }

    fun saveTracks(tracks: List<TrackEntity>) {
        tracksCache.saveTracks(tracks)
    }

}