package com.sebastianopighi.bestlyrics.presentation.di.modules

import android.content.Context
import com.sebastianopighi.bestlyrics.presentation.utils.ImageLoader
import com.sebastianopighi.bestlyrics.presentation.utils.PicassoImageLoader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(context: Context){

    private val appContext = context.applicationContext

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return appContext
    }

    @Singleton
    @Provides
    fun provideImageLoader(context: Context) : ImageLoader {
        return PicassoImageLoader(Picasso.with(context))
    }
}