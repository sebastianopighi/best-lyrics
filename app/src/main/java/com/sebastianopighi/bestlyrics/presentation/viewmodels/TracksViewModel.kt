package com.sebastianopighi.bestlyrics.presentation.viewmodels

import android.arch.lifecycle.MutableLiveData
import com.sebastianopighi.bestlyrics.domain.models.TrackEntity
import com.sebastianopighi.bestlyrics.domain.usecases.GetAlbumUseCase
import com.sebastianopighi.bestlyrics.domain.usecases.GetTracksUseCase
import com.sebastianopighi.bestlyrics.domain.utils.Mapper
import com.sebastianopighi.bestlyrics.presentation.activities.TracksViewState
import com.sebastianopighi.bestlyrics.presentation.models.Track
import com.sebastianopighi.bestlyrics.presentation.utils.BaseViewModel
import com.sebastianopighi.bestlyrics.presentation.utils.SingleLiveEvent

class TracksViewModel (private val getTracks: GetTracksUseCase,
                       private val getAlbum: GetAlbumUseCase,
                       private val trackEntityMapper: Mapper<TrackEntity, Track>): BaseViewModel() {

    private var tracksPage: Int = 0
    var viewState: MutableLiveData<TracksViewState> = MutableLiveData()
    var errorState: SingleLiveEvent<Throwable?> = SingleLiveEvent()

    init {
        viewState.value = TracksViewState()
    }


    fun getTracks() {
        tracksPage = tracksPage.inc()
        addDisposable(getTracks.getPage(tracksPage)
            .flatMap { trackEntityMapper.observable(it) }
            .doOnNext { tracks ->
                viewState.value?.let {
                    val allTracks = it.tracks ?: mutableListOf()
                    allTracks.addAll(it.addTracks ?: emptyList())
                    it.tracks = allTracks
                    it.addTracks = tracks
                }
            }
            .flatMapIterable { tracks -> tracks }
            .flatMap { getAlbum.getById(it.album_id) }
            .toList()
            .doOnSuccess { albums ->
                viewState.value?.let {
                    it.addTracks?.map { track ->
                        track.album_cover_url = albums.first { it?.album_id == track.album_id }?.album_coverart.orEmpty() }
                }
            }
            .subscribe({ tracks ->
                viewState.value?.let {
                    val newState = it.copy(showLoading = false)
                    this.viewState.value = newState
                    this.errorState.value = null
                }
            }, {
                viewState.value = viewState.value?.copy(showLoading = false)
                errorState.value = it
            }))
    }
}