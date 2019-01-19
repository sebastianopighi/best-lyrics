package com.sebastianopighi.bestlyrics.data.networking

import com.google.gson.annotations.SerializedName

data class ApiHeaderResponse(

    @SerializedName("status_code")
    var status_code: Int = 0

)