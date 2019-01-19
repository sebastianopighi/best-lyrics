package com.sebastianopighi.bestlyrics.domain.repositories

import com.sebastianopighi.bestlyrics.domain.models.AlbumEntity
import io.reactivex.Single

interface AlbumsCache {
    fun getAlbum(albumId: Int): Single<AlbumEntity?>
    fun saveAlbum(album: AlbumEntity)
}