package com.sebastianopighi.bestlyrics.data.repositories.album

import com.sebastianopighi.bestlyrics.data.models.mappers.AlbumDataToEntityMapper
import com.sebastianopighi.bestlyrics.data.networking.Api
import com.sebastianopighi.bestlyrics.domain.models.AlbumEntity
import com.sebastianopighi.bestlyrics.domain.repositories.AlbumsDataStore
import io.reactivex.Observable
import io.reactivex.Single

class AlbumsCloudDataStore(private val api: Api): AlbumsDataStore {

    private val albumDataMapper = AlbumDataToEntityMapper()

    override fun getAlbum(albumId: Int): Single<AlbumEntity?> {
        return api.getAlbum(albumId)
            .map { albumDataMapper.mapFrom(it.message?.body?.album)}
    }
}