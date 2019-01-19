package com.sebastianopighi.bestlyrics.domain.usecases

import com.sebastianopighi.bestlyrics.domain.models.LyricsEntity
import com.sebastianopighi.bestlyrics.domain.repositories.LyricsRepository
import com.sebastianopighi.bestlyrics.domain.utils.Transformer
import io.reactivex.Observable


open class GetLyricsUseCase(transformer: Transformer<LyricsEntity?>,
                            private val lyricsRepository: LyricsRepository
) : UseCase<LyricsEntity?>(transformer) {

    companion object {
        private const val PARAM_TRACK_ID = "track_id"
    }

    fun getById(trackId: Int): Observable<LyricsEntity?> {
        val data = HashMap<String, Int>()
        data[PARAM_TRACK_ID] = trackId
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<LyricsEntity?> {
        val lyricsId = data?.get(PARAM_TRACK_ID)
        lyricsId?.let {
            return lyricsRepository.getLyrics(it as Int).toObservable()
        } ?: return Observable.error({ IllegalArgumentException("TrackId must be provided.") })
    }
}