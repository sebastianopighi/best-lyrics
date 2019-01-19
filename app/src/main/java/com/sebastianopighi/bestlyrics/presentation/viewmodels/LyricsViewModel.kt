package com.sebastianopighi.bestlyrics.presentation.viewmodels

import android.arch.lifecycle.MutableLiveData
import com.sebastianopighi.bestlyrics.domain.models.AlbumEntity
import com.sebastianopighi.bestlyrics.domain.models.LyricsEntity
import com.sebastianopighi.bestlyrics.domain.models.TrackEntity
import com.sebastianopighi.bestlyrics.domain.usecases.GetAlbumUseCase
import com.sebastianopighi.bestlyrics.domain.usecases.GetLyricsUseCase
import com.sebastianopighi.bestlyrics.domain.usecases.GetTrackDetailsUseCase
import com.sebastianopighi.bestlyrics.domain.utils.Mapper
import com.sebastianopighi.bestlyrics.presentation.activities.LyricsViewState
import com.sebastianopighi.bestlyrics.presentation.models.Album
import com.sebastianopighi.bestlyrics.presentation.models.Lyrics
import com.sebastianopighi.bestlyrics.presentation.models.Track
import com.sebastianopighi.bestlyrics.presentation.utils.BaseViewModel
import com.sebastianopighi.bestlyrics.presentation.utils.SingleLiveEvent

class LyricsViewModel (private val getTracks: GetTrackDetailsUseCase,
                       private val getAlbum: GetAlbumUseCase,
                       private val getLyrics: GetLyricsUseCase,
                       private val tracksEntityMapper: Mapper<TrackEntity, Track>,
                       private val albumEntityMapper: Mapper<AlbumEntity, Album>,
                       private val lyricsEntityMapper: Mapper<LyricsEntity?, Lyrics?>,
                       private val trackId: Int): BaseViewModel() {

    var viewState: MutableLiveData<LyricsViewState> = MutableLiveData()
    var errorState: SingleLiveEvent<Throwable?> = SingleLiveEvent()

    init {
        viewState.value = LyricsViewState()
    }

    fun getLyrics() {
        addDisposable(getTracks.getById(trackId)
            .flatMap { tracksEntityMapper.observable(it) }
            .doOnNext { track ->
                viewState.value?.let {
                    val newState = it.copy(track = track)
                    this.viewState.value = newState
                    this.errorState.value = null
                }
            }.flatMap { getAlbum.getById(it.album_id) }
            .flatMap { albumEntityMapper.observable(it) }
            .doOnNext { album ->
                viewState.value?.let {
                    it.track?.album_cover_url = album.album_coverart
                    it.track?.album_release_date = album.album_release_date
                    val newState = it.copy()
                    this.viewState.value = newState
                }
            }
            .flatMap { getLyrics.getById(trackId) }
            .flatMap { lyricsEntityMapper.observable(it) }
            .subscribe({ lyrics ->
                viewState.value?.let {
                    val newState = it.copy(showLoading = false, lyrics = lyrics)
                    this.viewState.value = newState
                }

            }, {
                viewState.value = viewState.value?.copy(showLoading = false)
                errorState.value = it
            }))
    }

}