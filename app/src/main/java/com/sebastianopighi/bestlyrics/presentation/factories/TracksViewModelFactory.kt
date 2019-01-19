package com.sebastianopighi.bestlyrics.presentation.factories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sebastianopighi.bestlyrics.domain.models.TrackEntity
import com.sebastianopighi.bestlyrics.domain.usecases.GetAlbumUseCase
import com.sebastianopighi.bestlyrics.domain.usecases.GetTracksUseCase
import com.sebastianopighi.bestlyrics.domain.utils.Mapper
import com.sebastianopighi.bestlyrics.presentation.models.Track
import com.sebastianopighi.bestlyrics.presentation.viewmodels.TracksViewModel

class TracksViewModelFactory(private val tracksUseCase: GetTracksUseCase,
                             private val albumUseCase: GetAlbumUseCase,
                             private val mapper: Mapper<TrackEntity, Track>) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TracksViewModel(tracksUseCase, albumUseCase, mapper) as T
    }

}