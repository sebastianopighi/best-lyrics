package com.sebastianopighi.bestlyrics.presentation.factories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sebastianopighi.bestlyrics.domain.models.AlbumEntity
import com.sebastianopighi.bestlyrics.domain.models.LyricsEntity
import com.sebastianopighi.bestlyrics.domain.models.TrackEntity
import com.sebastianopighi.bestlyrics.domain.usecases.GetAlbumUseCase
import com.sebastianopighi.bestlyrics.domain.usecases.GetLyricsUseCase
import com.sebastianopighi.bestlyrics.domain.usecases.GetTrackDetailsUseCase
import com.sebastianopighi.bestlyrics.domain.utils.Mapper
import com.sebastianopighi.bestlyrics.presentation.models.Album
import com.sebastianopighi.bestlyrics.presentation.models.Lyrics
import com.sebastianopighi.bestlyrics.presentation.models.Track
import com.sebastianopighi.bestlyrics.presentation.viewmodels.LyricsViewModel

class LyricsViewModelFactory(private val trackDetailsUseCase: GetTrackDetailsUseCase,
                             private val albumUseCase: GetAlbumUseCase,
                             private val lyricsUseCase: GetLyricsUseCase,
                             private val trackMapper: Mapper<TrackEntity, Track>,
                             private val albumMapper: Mapper<AlbumEntity, Album>,
                             private val lyricsMapper: Mapper<LyricsEntity?, Lyrics?>) : ViewModelProvider.Factory {

    var trackId: Int = -1

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LyricsViewModel(trackDetailsUseCase,albumUseCase,lyricsUseCase,trackMapper,albumMapper,lyricsMapper,trackId) as T
    }

}