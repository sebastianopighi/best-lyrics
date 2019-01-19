package com.sebastianopighi.bestlyrics.presentation.di.modules

import com.sebastianopighi.bestlyrics.data.networking.Api
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule(private val baseUrl: String, private val apiKey: String) {

    @Singleton
    @Provides
    fun provideInterceptors(): ArrayList<Interceptor> {

        val interceptors = arrayListOf<Interceptor>()

        val keyInterceptor = Interceptor { chain ->

            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", apiKey)
                .build()

            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            return@Interceptor chain.proceed(request)
        }

        val logInterceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        interceptors.add(keyInterceptor)
        interceptors.add(logInterceptor)
        return interceptors
    }

    @Singleton
    @Provides
    fun provideRetrofit(interceptors: ArrayList<Interceptor>): Retrofit {

        val clientBuilder = OkHttpClient.Builder()
        if (!interceptors.isEmpty()) {
            interceptors.forEach { interceptor ->
                clientBuilder.addInterceptor(interceptor)
            }
        }
        return Retrofit.Builder()
            .client(clientBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

}