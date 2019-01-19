package com.sebastianopighi.bestlyrics.data.repositories.album

import com.sebastianopighi.bestlyrics.data.models.AlbumData
import com.sebastianopighi.bestlyrics.data.models.mappers.AlbumDataToEntityMapper
import com.sebastianopighi.bestlyrics.data.models.mappers.AlbumEntityToDataMapper
import com.sebastianopighi.bestlyrics.domain.models.AlbumEntity
import com.sebastianopighi.bestlyrics.domain.repositories.AlbumsCache
import io.reactivex.Single

class AlbumsMemoryCache: AlbumsCache {

    private val albumDataMapper = AlbumDataToEntityMapper()
    private val albumEntityMapper = AlbumEntityToDataMapper()
    private val albums = mutableMapOf<Int, AlbumData?>()

    override fun getAlbum(albumId: Int): Single<AlbumEntity?> {
        return albums.get(albumId)?.let {
            Single.just(albumDataMapper.mapFrom(it))
        } ?: Single.error(Throwable("No cached album found"))
    }

    override fun saveAlbum(album: AlbumEntity) {
        albums.put(album.album_id, albumEntityMapper.mapFrom(album))
    }

}