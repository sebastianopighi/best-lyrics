package com.sebastianopighi.bestlyrics.domain.usecases

import com.sebastianopighi.bestlyrics.domain.models.TrackEntity
import com.sebastianopighi.bestlyrics.domain.repositories.TracksRepository
import com.sebastianopighi.bestlyrics.domain.utils.Transformer
import io.reactivex.Observable


open class GetTrackDetailsUseCase(transformer: Transformer<TrackEntity>,
                                  private val tracksRepository: TracksRepository
) : UseCase<TrackEntity>(transformer) {

    companion object {
        private const val PARAM_TRACK_ID = "track_id"
    }

    fun getById(trackId: Int): Observable<TrackEntity> {
        val data = HashMap<String, Int>()
        data[PARAM_TRACK_ID] = trackId
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<TrackEntity> {
        val trackId = data?.get(PARAM_TRACK_ID)
        trackId?.let {
            return tracksRepository.getTrack(it as Int).toObservable()
        } ?: return Observable.error({ IllegalArgumentException("TrackId must be provided.") })
    }

}