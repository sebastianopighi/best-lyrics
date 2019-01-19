package com.sebastianopighi.bestlyrics.data.repositories.tracks

import com.sebastianopighi.bestlyrics.data.models.TrackData
import com.sebastianopighi.bestlyrics.data.models.mappers.TrackDataToEntityMapper
import com.sebastianopighi.bestlyrics.data.models.mappers.TrackEntityToDataMapper
import com.sebastianopighi.bestlyrics.domain.models.TrackEntity
import com.sebastianopighi.bestlyrics.domain.repositories.TracksCache
import io.reactivex.Single

class TracksMemoryCache: TracksCache {

    private val trackDataMapper = TrackDataToEntityMapper()
    private val trackEntityMapper = TrackEntityToDataMapper()
    private val tracks = mutableListOf<TrackData>()

    override fun getAllTracks(): Single<List<TrackEntity?>> {
        return Single.just(tracks.let { it.map { trackDataMapper.mapFrom(it) }})
    }

    override fun getTrack(trackId: Int): Single<TrackEntity> {
        return Single.just(trackDataMapper.mapFrom(tracks.first { it.track_id == trackId }))
    }

    override fun saveTracks(tracks: List<TrackEntity>) {
        this.tracks.addAll(tracks.map { trackEntityMapper.mapFrom(it) })
    }

}