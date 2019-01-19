package com.sebastianopighi.bestlyrics.domain.utils

import io.reactivex.Observable
import io.reactivex.Single

abstract class Mapper<in E, T> {
    abstract fun mapFrom(from: E): T

    fun observable(from: E): Observable<T> {
        return Observable.fromCallable { mapFrom(from) }
    }

    fun observable(from: List<E>): Observable<List<T>> {
        return Observable.fromCallable { from.map { mapFrom(it) } }
    }

    fun single(from: E): Single<T> {
        return Single.fromCallable { mapFrom(from) }
    }

    fun single(from: List<E>): Single<List<T>> {
        return Single.fromCallable { from.map { mapFrom(it) } }
    }

}