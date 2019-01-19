package com.sebastianopighi.bestlyrics.data.networking.album

import com.google.gson.annotations.SerializedName
import com.sebastianopighi.bestlyrics.data.models.AlbumData


data class AlbumBodyResponse(

    @SerializedName("album")
    var album: AlbumData? = null

)