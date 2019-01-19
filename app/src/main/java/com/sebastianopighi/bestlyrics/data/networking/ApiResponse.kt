package com.sebastianopighi.bestlyrics.data.networking

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(

    @SerializedName("message")
    var message: ApiMessageResponse<T>? = null

)