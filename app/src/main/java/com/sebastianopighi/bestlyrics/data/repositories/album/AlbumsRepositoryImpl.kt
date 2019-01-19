package com.sebastianopighi.bestlyrics.data.repositories.album

import com.sebastianopighi.bestlyrics.domain.models.AlbumEntity
import com.sebastianopighi.bestlyrics.domain.repositories.AlbumsRepository
import io.reactivex.Single

class AlbumsRepositoryImpl(private val cacheDataStore: AlbumsCacheDataStore,
                           private val cloudDataStore: AlbumsCloudDataStore
): AlbumsRepository {

    override fun getAlbum(albumId: Int): Single<AlbumEntity?> {
        return cacheDataStore.getAlbum(albumId)
            .onErrorResumeNext(cloudDataStore.getAlbum(albumId)
                .doOnSuccess{ it?.let { cacheDataStore.saveAlbum(it) } })
    }
}