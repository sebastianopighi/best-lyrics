package com.sebastianopighi.bestlyrics.presentation

import android.app.Application
import com.sebastianopighi.bestlyrics.R
import com.sebastianopighi.bestlyrics.presentation.di.DaggerMainComponent
import com.sebastianopighi.bestlyrics.presentation.di.MainComponent
import com.sebastianopighi.bestlyrics.presentation.di.components.LyricsSubComponent
import com.sebastianopighi.bestlyrics.presentation.di.components.TracksSubComponent
import com.sebastianopighi.bestlyrics.presentation.di.modules.*

class App: Application() {

    lateinit var mainComponent: MainComponent
    private var tracksComponent: TracksSubComponent? = null
    private var lyricsComponent: LyricsSubComponent? = null

    override fun onCreate() {
        super.onCreate()

        initDependencies()
    }

    private fun initDependencies() {
        mainComponent = DaggerMainComponent.builder()
            .appModule(AppModule(applicationContext))
            .networkModule(NetworkModule(getString(R.string.api_base_url), getString(R.string.api_key)))
            .dataModule(DataModule())
            .build()

    }

    fun createTracksComponent(): TracksSubComponent {
        tracksComponent = mainComponent.add(TracksModule())
        return tracksComponent!!
    }

    fun releaseTracksComponent() {
        tracksComponent = null
    }

    fun createLyricsComponent(): LyricsSubComponent {
        lyricsComponent = mainComponent.add(LyricsModule())
        return lyricsComponent!!
    }

    fun releaseLyricsComponent() {
        lyricsComponent = null
    }
}