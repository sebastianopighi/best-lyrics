package com.sebastianopighi.bestlyrics.presentation.models.mappers

import com.sebastianopighi.bestlyrics.domain.models.LyricsEntity
import com.sebastianopighi.bestlyrics.domain.utils.Mapper
import com.sebastianopighi.bestlyrics.presentation.models.Lyrics
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LyricsEntityMapper @Inject constructor() : Mapper<LyricsEntity?, Lyrics?>() {

    override fun mapFrom(from: LyricsEntity?): Lyrics? {
        return from?.let {
            Lyrics(
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