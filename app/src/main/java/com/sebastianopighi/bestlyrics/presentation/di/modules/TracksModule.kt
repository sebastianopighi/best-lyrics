package com.sebastianopighi.bestlyrics.presentation.di.modules

import com.sebastianopighi.bestlyrics.domain.repositories.AlbumsRepository
import com.sebastianopighi.bestlyrics.domain.repositories.TracksRepository
import com.sebastianopighi.bestlyrics.domain.usecases.GetAlbumUseCase
import com.sebastianopighi.bestlyrics.domain.usecases.GetTracksUseCase
import com.sebastianopighi.bestlyrics.presentation.factories.TracksViewModelFactory
import com.sebastianopighi.bestlyrics.presentation.models.mappers.TrackEntityMapper
import com.sebastianopighi.bestlyrics.presentation.utils.SchedulerTransformer
import dagger.Module
import dagger.Provides

@Module
class TracksModule {

    @Provides
    fun provideGetTracksUseCase(tracksRepository: TracksRepository): GetTracksUseCase {
        return GetTracksUseCase(SchedulerTransformer(), tracksRepository)
    }

    @Provides
    fun provideGetAlbumUseCase(albumsRepository: AlbumsRepository): GetAlbumUseCase {
        return GetAlbumUseCase(SchedulerTransformer(), albumsRepository)
    }

    @Provides
    fun provideTracksViewModelFactory(tracksUseCase: GetTracksUseCase, albumUseCase: GetAlbumUseCase, mapper: TrackEntityMapper): TracksViewModelFactory {
        return TracksViewModelFactory(tracksUseCase, albumUseCase, mapper)
    }
}