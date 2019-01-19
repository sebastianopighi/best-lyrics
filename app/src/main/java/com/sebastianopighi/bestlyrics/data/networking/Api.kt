package com.sebastianopighi.bestlyrics.data.networking

import com.sebastianopighi.bestlyrics.data.networking.album.AlbumBodyResponse
import com.sebastianopighi.bestlyrics.data.networking.lyrics.LyricsBodyResponse
import com.sebastianopighi.bestlyrics.data.networking.tracks.TracksBodyResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("chart.tracks.get")
    fun getTracks(@Query("chart_name") chartName: String, @Query("page") page: Int,
                  @Query("page_size") pageSize: Int, @Query("country") country: String): Single<ApiResponse<TracksBodyResponse>>

    @GET("album.get")
    fun getAlbum(@Query("album_id") albumId: Int): Single<ApiResponse<AlbumBodyResponse>>

    @GET("track.lyrics.get")
    fun getLyrics(@Query("track_id") trackId: Int): Single<ApiResponse<LyricsBodyResponse>>

}