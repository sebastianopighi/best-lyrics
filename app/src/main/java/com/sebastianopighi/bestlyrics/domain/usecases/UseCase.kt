package com.sebastianopighi.bestlyrics.domain.usecases

import com.sebastianopighi.bestlyrics.domain.utils.Transformer
import io.reactivex.Observable

abstract class UseCase<T>(private val transformer: Transformer<T>) {

    abstract fun createObservable(data: Map<String, Any>? = null): Observable<T>

    fun observable(withData: Map<String, Any>? = null): Observable<T> {
        return createObservable(withData).compose(transformer)
    }
}