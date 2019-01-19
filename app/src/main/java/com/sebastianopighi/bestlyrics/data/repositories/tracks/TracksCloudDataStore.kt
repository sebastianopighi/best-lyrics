package com.sebastianopighi.bestlyrics.data.repositories.tracks

import com.sebastianopighi.bestlyrics.data.models.mappers.TrackDataToEntityMapper
import com.sebastianopighi.bestlyrics.data.networking.Api
import com.sebastianopighi.bestlyrics.domain.models.TrackEntity
import com.sebastianopighi.bestlyrics.domain.repositories.TracksDataStore
import io.reactivex.Single

class TracksCloudDataStore(private val api: Api): TracksDataStore {

    companion object {
        private const val PARAM_CHART_NAME = "top"
        private const val PARAM_PAGE_SIZE = 20
        private const val PARAM_COUNTRY = "it"
    }

    private val trackDataMapper = TrackDataToEntityMapper()

    override fun getTracks(page: Int): Single<List<TrackEntity?>> {
        return api.getTracks(PARAM_CHART_NAME, page, PARAM_PAGE_SIZE, PARAM_COUNTRY)
            .map { it.message?.body?.track_list?.map { trackDataMapper.mapFrom(it.track) } ?: emptyList() }
    }

}