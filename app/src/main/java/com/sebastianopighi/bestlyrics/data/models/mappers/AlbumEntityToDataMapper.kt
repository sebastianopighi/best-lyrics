package com.sebastianopighi.bestlyrics.data.models.mappers

import com.sebastianopighi.bestlyrics.data.models.AlbumData
import com.sebastianopighi.bestlyrics.domain.models.AlbumEntity
import com.sebastianopighi.bestlyrics.domain.utils.Mapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumEntityToDataMapper @Inject constructor() : Mapper<AlbumEntity?, AlbumData?>() {

    override fun mapFrom(from: AlbumEntity?): AlbumData? {
        return from?.let {
            AlbumData(
                album_id = it.album_id,
                album_name = it.album_name,
                album_rating = it.album_rating,
                album_track_count = it.album_track_count,
                album_release_date = it.album_release_date,
                artist_id = it.artist_id,
                artist_name = it.artist_name,
                album_copyright = it.album_copyright,
                album_label = it.album_label,
                album_coverart = it.album_coverart
            )
        }
    }
}