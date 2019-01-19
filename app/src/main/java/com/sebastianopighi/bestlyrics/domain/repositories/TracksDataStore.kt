package com.sebastianopighi.bestlyrics.domain.repositories

import com.sebastianopighi.bestlyrics.domain.models.TrackEntity
import io.reactivex.Single

interface TracksDataStore {
    fun getTracks(page: Int): Single<List<TrackEntity?>>
}