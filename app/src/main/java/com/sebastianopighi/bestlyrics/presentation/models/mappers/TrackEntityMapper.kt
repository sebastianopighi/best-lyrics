package com.sebastianopighi.bestlyrics.presentation.models.mappers

import com.sebastianopighi.bestlyrics.domain.models.TrackEntity
import com.sebastianopighi.bestlyrics.domain.utils.Mapper
import com.sebastianopighi.bestlyrics.presentation.models.Track
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrackEntityMapper @Inject constructor() : Mapper<TrackEntity, Track>() {

    override fun mapFrom(from: TrackEntity): Track {
        return Track(
            track_id = from.track_id,
            track_name = from.track_name,
            album_id = from.album_id,
            album_name = from.album_name,
            album_cover_url = from.album_cover_url,
            album_release_date = from.album_release_date,
            artist_id = from.artist_id,
            artist_name = from.artist_name
        )
    }

}