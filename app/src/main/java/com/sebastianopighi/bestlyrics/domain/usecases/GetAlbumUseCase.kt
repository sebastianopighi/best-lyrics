package com.sebastianopighi.bestlyrics.domain.usecases

import com.sebastianopighi.bestlyrics.domain.models.AlbumEntity
import com.sebastianopighi.bestlyrics.domain.repositories.AlbumsRepository
import com.sebastianopighi.bestlyrics.domain.utils.Transformer
import io.reactivex.Observable

open class GetAlbumUseCase(transformer: Transformer<AlbumEntity?>,
                           private val albumsRepository: AlbumsRepository
) : UseCase<AlbumEntity?>(transformer) {

    companion object {
        private const val PARAM_ALBUM_ID = "album_id"
    }

    fun getById(albumId: Int): Observable<AlbumEntity?> {
        val data = HashMap<String, Int>()
        data[PARAM_ALBUM_ID] = albumId
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<AlbumEntity?> {
        val albumId = data?.get(PARAM_ALBUM_ID)
        albumId?.let {
            return albumsRepository.getAlbum(it as Int).toObservable()
        } ?: return Observable.error({ IllegalArgumentException("AlbumId must be provided.") })
    }
}