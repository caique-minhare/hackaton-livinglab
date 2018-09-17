package br.com.igym.view.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.igym.util.observe
import br.com.igym.util.view.Arguments.ARG_USER
import br.com.igym.view.dashboard.MainActivity
import br.com.igym.view.login.LoginActivity
import com.google.firebase.auth.FirebaseUser
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.koin.android.architecture.ext.viewModel

class SplashScreenActivity : AppCompatActivity() {

    private val viewModel: SplashScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
        subscribeUi()
        viewModel.verifyLogged()
    }

    private fun subscribeUi() {
        viewModel.notLogged.observe(this, ::onUserNotLogged)
        viewModel.user.observe(this, ::onUserLogged)
    }

    private fun onUserNotLogged(notLogged: Boolean?) {
        notLogged?.let {
            startActivity(intentFor<LoginActivity>().newTask().clearTask())
        }
    }

    private fun onUserLogged(firebaseUser: FirebaseUser?) {
        firebaseUser?.let {
            startActivity(intentFor<MainActivity>(ARG_USER to it).newTask().clearTask())
        }
    }
}
