package com.sebastianopighi.bestlyrics.domain.repositories

import com.sebastianopighi.bestlyrics.domain.models.TrackEntity
import io.reactivex.Single

interface TracksCache {
    fun getAllTracks(): Single<List<TrackEntity?>>
    fun getTrack(trackId: Int): Single<TrackEntity>
    fun saveTracks(tracks: List<TrackEntity>)
}