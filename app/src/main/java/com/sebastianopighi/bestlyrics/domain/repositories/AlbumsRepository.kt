package com.sebastianopighi.bestlyrics.domain.repositories

import com.sebastianopighi.bestlyrics.domain.models.AlbumEntity
import io.reactivex.Single

interface AlbumsRepository {
    fun getAlbum(albumId: Int): Single<AlbumEntity?>
}