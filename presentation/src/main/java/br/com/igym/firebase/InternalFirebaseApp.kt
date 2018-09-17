package br.com.igym.firebase

import com.google.firebase.FirebaseApp
import io.reactivex.Single

interface InternalFirebaseApp {
    fun init(): Single<FirebaseApp>
}