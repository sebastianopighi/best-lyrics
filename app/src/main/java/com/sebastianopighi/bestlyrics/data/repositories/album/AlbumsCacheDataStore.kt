package com.sebastianopighi.bestlyrics.data.repositories.album

import com.sebastianopighi.bestlyrics.domain.models.AlbumEntity
import com.sebastianopighi.bestlyrics.domain.repositories.AlbumsCache
import com.sebastianopighi.bestlyrics.domain.repositories.AlbumsDataStore
import io.reactivex.Observable
import io.reactivex.Single

class AlbumsCacheDataStore(private val albumCache: AlbumsCache):
    AlbumsDataStore {
    override fun getAlbum(albumId: Int): Single<AlbumEntity?> {
        return albumCache.getAlbum(albumId)
    }

    fun saveAlbum(album: AlbumEntity) {
        albumCache.saveAlbum(album)
    }

}