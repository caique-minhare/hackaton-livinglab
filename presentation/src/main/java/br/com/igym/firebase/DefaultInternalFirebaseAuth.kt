package br.com.igym.firebase

import com.google.firebase.auth.FirebaseAuth
import br.com.igym.domain.di.SchedulerProvider
import br.com.igym.util.rx.with
import io.reactivex.Single

class DefaultInternalFirebaseAuth(
        private val schedulerProvider: SchedulerProvider,
        private val internalFirebaseAppRepository: InternalFirebaseApp
) : InternalFirebaseAuth {

    override fun firebaseAuth() = internalFirebaseAppRepository.init().run {
        with(schedulerProvider).flatMap {
            Single.just(FirebaseAuth(it))
        }
    }
}