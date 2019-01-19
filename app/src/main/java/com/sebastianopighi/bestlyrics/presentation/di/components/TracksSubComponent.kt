package com.sebastianopighi.bestlyrics.presentation.di.components

import com.sebastianopighi.bestlyrics.presentation.activities.TracksActivity
import com.sebastianopighi.bestlyrics.presentation.di.modules.TracksModule
import com.sebastianopighi.bestlyrics.presentation.di.scopes.TracksScopes
import dagger.Subcomponent

@TracksScopes
@Subcomponent(modules = [TracksModule::class])
interface TracksSubComponent {
    fun inject(tracksActivity: TracksActivity)
}