package com.sebastianopighi.bestlyrics.data.models.mappers

import com.sebastianopighi.bestlyrics.data.models.TrackData
import com.sebastianopighi.bestlyrics.domain.models.TrackEntity
import com.sebastianopighi.bestlyrics.domain.utils.Mapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrackDataToEntityMapper @Inject constructor() : Mapper<TrackData?, TrackEntity?>() {

    override fun mapFrom(from: TrackData?): TrackEntity? {
        return from?.let {TrackEntity(
            track_id = from.track_id,
            track_name = from.track_name,
            track_rating = from.track_rating,
            explicit = from.explicit,
            has_lyrics = from.has_lyrics,
            has_subtitles = from.has_subtitles,
            num_favourite = from.num_favourite,
            album_id = from.album_id,
            album_name = from.album_name,
            artist_id = from.artist_id,
            artist_name = from.artist_name,
            track_share_url = from.track_share_url
        )}
    }
}