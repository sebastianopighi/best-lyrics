package com.sebastianopighi.bestlyrics.presentation.di.modules

import com.sebastianopighi.bestlyrics.data.networking.Api
import com.sebastianopighi.bestlyrics.data.repositories.album.AlbumsCacheDataStore
import com.sebastianopighi.bestlyrics.data.repositories.album.AlbumsCloudDataStore
import com.sebastianopighi.bestlyrics.data.repositories.album.AlbumsMemoryCache
import com.sebastianopighi.bestlyrics.data.repositories.album.AlbumsRepositoryImpl
import com.sebastianopighi.bestlyrics.data.repositories.lyrics.LyricsCacheDataStore
import com.sebastianopighi.bestlyrics.data.repositories.lyrics.LyricsCloudDataStore
import com.sebastianopighi.bestlyrics.data.repositories.lyrics.LyricsMemoryCache
import com.sebastianopighi.bestlyrics.data.repositories.lyrics.LyricsRepositoryImpl
import com.sebastianopighi.bestlyrics.data.repositories.tracks.TracksCacheDataStore
import com.sebastianopighi.bestlyrics.data.repositories.tracks.TracksCloudDataStore
import com.sebastianopighi.bestlyrics.data.repositories.tracks.TracksMemoryCache
import com.sebastianopighi.bestlyrics.data.repositories.tracks.TracksRepositoryImpl
import com.sebastianopighi.bestlyrics.domain.repositories.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class DataModule {

    @Provides
    @Singleton
    fun provideTracksRepository(api: Api,
                                cache: TracksCache
    ): TracksRepository {

        val tracksCacheDataStore = TracksCacheDataStore(cache)
        val tracksCloudDataStore = TracksCloudDataStore(api)
        return TracksRepositoryImpl(
            tracksCacheDataStore,
            tracksCloudDataStore
        )
    }

    @Singleton
    @Provides
    fun provideTracksCache(): TracksCache {
        return TracksMemoryCache()
    }

    @Provides
    @Singleton
    fun provideAlbumsRepository(api: Api,
                                cache: AlbumsCache
    ): AlbumsRepository {

        val albumsCacheDataStore = AlbumsCacheDataStore(cache)
        val albumsCloudDataStore = AlbumsCloudDataStore(api)
        return AlbumsRepositoryImpl(albumsCacheDataStore, albumsCloudDataStore)
    }

    @Singleton
    @Provides
    fun provideAlbumsCache(): AlbumsCache {
        return AlbumsMemoryCache()
    }

    @Provides
    @Singleton
    fun provideLyricsRepository(api: Api,
                                cache: LyricsCache
    ): LyricsRepository {

        val lyricsCacheDataStore = LyricsCacheDataStore(cache)
        val lyricsCloudDataStore = LyricsCloudDataStore(api)
        return LyricsRepositoryImpl(
            lyricsCacheDataStore,
            lyricsCloudDataStore
        )
    }

    @Singleton
    @Provides
    fun provideLyricsCache(): LyricsCache {
        return LyricsMemoryCache()
    }
}