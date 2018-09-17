package br.com.igym.util.rx

import br.com.igym.domain.di.SchedulerProvider
import io.reactivex.Single

fun <T> Single<T>.with(schedulerProvider: SchedulerProvider): Single<T> = observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())