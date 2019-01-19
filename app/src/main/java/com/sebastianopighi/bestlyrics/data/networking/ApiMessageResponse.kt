package com.sebastianopighi.bestlyrics.data.networking

import com.google.gson.annotations.SerializedName

data class ApiMessageResponse<T>(

    @SerializedName("header")
    var header: ApiHeaderResponse? = null,

    @SerializedName("body")
    var body: T? = null

)