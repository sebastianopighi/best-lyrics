package com.sebastianopighi.bestlyrics.presentation.di.components

import com.sebastianopighi.bestlyrics.presentation.activities.LyricsActivity
import com.sebastianopighi.bestlyrics.presentation.di.modules.LyricsModule
import com.sebastianopighi.bestlyrics.presentation.di.scopes.TracksScopes
import dagger.Subcomponent

@TracksScopes
@Subcomponent(modules = [LyricsModule::class])
interface LyricsSubComponent {
    fun inject(lyricsActivity: LyricsActivity)
}