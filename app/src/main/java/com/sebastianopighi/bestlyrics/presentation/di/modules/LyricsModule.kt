package com.sebastianopighi.bestlyrics.presentation.di.modules

import com.sebastianopighi.bestlyrics.domain.repositories.AlbumsRepository
import com.sebastianopighi.bestlyrics.domain.repositories.LyricsRepository
import com.sebastianopighi.bestlyrics.domain.repositories.TracksRepository
import com.sebastianopighi.bestlyrics.domain.usecases.GetAlbumUseCase
import com.sebastianopighi.bestlyrics.domain.usecases.GetLyricsUseCase
import com.sebastianopighi.bestlyrics.domain.usecases.GetTrackDetailsUseCase
import com.sebastianopighi.bestlyrics.presentation.factories.LyricsViewModelFactory
import com.sebastianopighi.bestlyrics.presentation.models.mappers.AlbumEntityMapper
import com.sebastianopighi.bestlyrics.presentation.models.mappers.LyricsEntityMapper
import com.sebastianopighi.bestlyrics.presentation.models.mappers.TrackEntityMapper
import com.sebastianopighi.bestlyrics.presentation.utils.SchedulerTransformer
import dagger.Module
import dagger.Provides

@Module
class LyricsModule {

    @Provides
    fun provideGetTrackDetailsUseCase(tracksRepository: TracksRepository): GetTrackDetailsUseCase {
        return GetTrackDetailsUseCase(SchedulerTransformer(), tracksRepository)
    }

    @Provides
    fun provideGetAlbumUseCase(albumsRepository: AlbumsRepository): GetAlbumUseCase {
        return GetAlbumUseCase(SchedulerTransformer(), albumsRepository)
    }

    @Provides
    fun provideGetLyricsUseCase(lyricsRepository: LyricsRepository): GetLyricsUseCase {
        return GetLyricsUseCase(SchedulerTransformer(), lyricsRepository)
    }

    @Provides
    fun provideLyricsViewModelFactory(trackDetailsUseCase: GetTrackDetailsUseCase, albumUseCase: GetAlbumUseCase,
                                      lyricsUseCase: GetLyricsUseCase, trackMapper: TrackEntityMapper,
                                      albumMapper: AlbumEntityMapper, lyricsMapper: LyricsEntityMapper): LyricsViewModelFactory {
        return LyricsViewModelFactory(trackDetailsUseCase, albumUseCase, lyricsUseCase, trackMapper, albumMapper, lyricsMapper)
    }
}