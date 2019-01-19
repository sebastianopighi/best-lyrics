package com.sebastianopighi.bestlyrics.domain.usecases

import com.sebastianopighi.bestlyrics.domain.models.TrackEntity
import com.sebastianopighi.bestlyrics.domain.repositories.TracksRepository
import com.sebastianopighi.bestlyrics.domain.utils.Transformer
import io.reactivex.Observable


open class GetTracksUseCase(transformer: Transformer<List<TrackEntity>>,
                            private val tracksRepository: TracksRepository
) : UseCase<List<TrackEntity>>(transformer) {


    companion object {
        private const val PARAM_PAGE = "page"
    }

    fun getPage(page: Int): Observable<List<TrackEntity>> {
        val data = HashMap<String, Int>()
        data[GetTracksUseCase.PARAM_PAGE] = page
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<List<TrackEntity>> {
        val page = data?.get(GetTracksUseCase.PARAM_PAGE)
        page?.let {
            return tracksRepository.getTracks(page as Int).toObservable()
        } ?: return tracksRepository.getTracks(1).toObservable()
    }

}