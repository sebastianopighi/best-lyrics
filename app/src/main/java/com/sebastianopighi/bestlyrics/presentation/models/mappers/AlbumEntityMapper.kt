package com.sebastianopighi.bestlyrics.presentation.models.mappers

import com.sebastianopighi.bestlyrics.domain.models.AlbumEntity
import com.sebastianopighi.bestlyrics.domain.utils.Mapper
import com.sebastianopighi.bestlyrics.presentation.models.Album
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumEntityMapper @Inject constructor() : Mapper<AlbumEntity, Album>() {

    override fun mapFrom(from: AlbumEntity): Album {
        return Album(
            album_id = from.album_id,
            album_name = from.album_name,
            album_rating = from.album_rating,
            album_track_count = from.album_track_count,
            album_release_date = from.album_release_date,
            album_label = from.album_label,
            album_coverart = from.album_coverart
        )
    }

}