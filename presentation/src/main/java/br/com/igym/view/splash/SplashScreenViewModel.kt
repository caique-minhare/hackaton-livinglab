package br.com.igym.view.splash

import android.arch.lifecycle.LiveData
import android.util.Log
import br.com.igym.base.BaseViewModel
import br.com.igym.firebase.GetCurrentUser
import br.com.igym.util.FlexibleLiveData
import com.google.firebase.auth.FirebaseUser

class SplashScreenViewModel(
        private val getCurrentUser: GetCurrentUser
) : BaseViewModel() {

    val notLogged: LiveData<Boolean> get() = notLoggedLiveData
    val user: LiveData<FirebaseUser> get() = userLiveData

    private val notLoggedLiveData: FlexibleLiveData<Boolean> = FlexibleLiveData()
    private val userLiveData: FlexibleLiveData<FirebaseUser> = FlexibleLiveData()

    fun verifyLogged() {
        launch { getCurrentUser.isAuthenticated().subscribe(::onSuccess, ::onFailure) }
    }

    private fun onSuccess(isLogged: Boolean) {
        if (isLogged) getCurrentUser()
        else notLoggedLiveData.value = isLogged
    }

    private fun getCurrentUser() {
        launch { getCurrentUser.getUser().subscribe(::onUserLogged, ::onFailure) }
    }

    private fun onUserLogged(firebaseUser: FirebaseUser) {
        userLiveData.value = firebaseUser
    }

    private fun onFailure(throwable: Throwable) {
        Log.e("onFailure", throwable.message)
    }
}