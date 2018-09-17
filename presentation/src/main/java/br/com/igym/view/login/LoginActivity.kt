package br.com.igym.view.login

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import br.com.igym.R
import br.com.igym.databinding.ActivityLoginBinding
import br.com.igym.databinding.ActivityLoginMode1Binding
import br.com.igym.util.observe
import br.com.igym.util.view.Arguments.ARG_USER
import com.google.firebase.auth.FirebaseUser
import br.com.igym.view.dashboard.MainActivity
import com.jakewharton.rxbinding2.widget.textChanges
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.koin.android.architecture.ext.viewModel

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()

    private lateinit var binding: ActivityLoginMode1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_mode_1)
        lifecycle.addObserver(loginViewModel)
        subscribeUi()
    }

    private fun subscribeUi() {
        loginViewModel.userLogged.observe(this, ::onLogInAuthorized)
        loginViewModel.userSigInFailed.observe(this, ::onSigInFailed)
//        binding.fieldPassword.textChanges().subscribe { loginViewModel.onGetPasswordText(it.toString()) }
//        binding.fieldEmail.textChanges().subscribe { loginViewModel.onGetEmailText(it.toString()) }
//        loginViewModel.isSignInEnabled.subscribe { binding.button.isEnabled = it }
//        binding.button.setOnClickListener { loginViewModel.signIn() }
    }

    private fun onSigInFailed(errorMsg: String?) {
        errorMsg?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun onLogInAuthorized(firebaseUser: FirebaseUser?) {
        firebaseUser?.let {
            startActivity(intentFor<MainActivity>(ARG_USER to it).newTask().clearTask())
        }
    }
}