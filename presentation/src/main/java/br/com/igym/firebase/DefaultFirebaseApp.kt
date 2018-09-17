package br.com.igym.firebase

import android.content.Context
import com.google.firebase.FirebaseApp
import io.reactivex.Single

class DefaultFirebaseApp(context: Context) : InternalFirebaseApp {
    private val context = context.applicationContext

    override fun init(): Single<FirebaseApp> {
        return Single.just(FirebaseApp.initializeApp(context))
    }
}