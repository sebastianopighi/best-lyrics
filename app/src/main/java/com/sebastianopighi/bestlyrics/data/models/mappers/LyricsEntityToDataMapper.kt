package com.sebastianopighi.bestlyrics.data.models.mappers

import com.sebastianopighi.bestlyrics.data.models.LyricsData
import com.sebastianopighi.bestlyrics.domain.models.LyricsEntity
import com.sebastianopighi.bestlyrics.domain.utils.Mapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LyricsEntityToDataMapper @Inject constructor() : Mapper<LyricsEntity?, LyricsData?>() {

    override fun mapFrom(from: LyricsEntity?): LyricsData? {
        return from?.let {
            LyricsData(
                lyrics_id = it.lyrics_id,
                explicit = it.explicit,
                lyrics_body = it.lyrics_body,
                pixel_tracking_url = it.pixel_tracking_url,
                lyrics_copyright = it.lyrics_copyright,
                updated_time = it.updated_time
            )
        }
    }
}