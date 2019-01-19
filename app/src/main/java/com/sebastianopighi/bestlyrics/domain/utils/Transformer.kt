package com.sebastianopighi.bestlyrics.domain.utils

import io.reactivex.ObservableTransformer

abstract class Transformer<T> : ObservableTransformer<T, T>