package com.sebastianopighi.bestlyrics.presentation.utils

import android.widget.ImageView

interface ImageLoader {
    fun load(url: String, imageView: ImageView, callback: (Boolean) -> Unit)
    fun load(url: String, imageView: ImageView, fadeEffect: Boolean = true)
}