package br.com.igym.firebase

import br.com.igym.domain.di.SchedulerProvider
import br.com.igym.util.rx.with
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Single

class GetCurrentUser(
        private val firebaseAuth: InternalFirebaseAuth,
        private val schedulerProvider: SchedulerProvider
) {

    fun isAuthenticated(): Single<Boolean> {
        return firebaseAuth.firebaseAuth().with(schedulerProvider).map { it.currentUser != null }
    }

    fun getUserId(): Single<String?> {
        return firebaseAuth.firebaseAuth().with(schedulerProvider).map { it.currentUser?.uid }
    }

    fun getUser(): Single<FirebaseUser> {
        return firebaseAuth.firebaseAuth().with(schedulerProvider).map { it.currentUser }
    }
}