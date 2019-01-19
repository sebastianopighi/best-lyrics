package com.sebastianopighi.bestlyrics.domain.repositories

import com.sebastianopighi.bestlyrics.domain.models.AlbumEntity
import io.reactivex.Single


interface AlbumsDataStore {
    fun getAlbum(albumId: Int): Single<AlbumEntity?>
}