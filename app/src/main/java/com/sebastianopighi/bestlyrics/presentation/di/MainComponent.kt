package com.sebastianopighi.bestlyrics.presentation.di

import com.sebastianopighi.bestlyrics.presentation.di.components.LyricsSubComponent
import com.sebastianopighi.bestlyrics.presentation.di.components.TracksSubComponent
import com.sebastianopighi.bestlyrics.presentation.di.modules.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AppModule::class),
    (NetworkModule::class),
    (DataModule::class)
])

interface MainComponent {
    fun add(tracksModule: TracksModule): TracksSubComponent
    fun add(lyricsModule: LyricsModule): LyricsSubComponent
}