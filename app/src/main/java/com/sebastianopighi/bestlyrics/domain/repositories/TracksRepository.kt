package com.sebastianopighi.bestlyrics.domain.repositories

import com.sebastianopighi.bestlyrics.domain.models.TrackEntity
import io.reactivex.Single

interface TracksRepository {
    fun getTracks(page: Int): Single<List<TrackEntity>>
    fun getTrack(trackId: Int): Single<TrackEntity>
}