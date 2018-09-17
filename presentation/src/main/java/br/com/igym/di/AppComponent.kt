package br.com.igym.di

import android.content.Context
import br.com.igym.data.di.RepositoryComponents
import br.com.igym.domain.di.SchedulerProvider
import br.com.igym.firebase.*
import br.com.igym.util.FIREBASE_USER
import br.com.igym.util.rx.DefaultSchedulerProvider
import br.com.igym.view.dashboard.MainViewModel
import br.com.igym.view.login.LoginViewModel
import br.com.igym.view.splash.SplashScreenViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val rxModule = applicationContext {
    bean { DefaultSchedulerProvider() as SchedulerProvider }
}

val viewModule = applicationContext {
    viewModel { params -> MainViewModel(params[FIREBASE_USER], get(), get()) }
    viewModel { SplashScreenViewModel(get()) }
    viewModel { LoginViewModel(get(), get(), get(), get()) }
//    bean { SigInValidation() }
}

val firebaseDependencies = applicationContext {
    bean { DefaultFirebaseApp(get() as Context) as InternalFirebaseApp }
    bean { DefaultInternalFirebaseAuth(get(), get()) as InternalFirebaseAuth }
    bean { GetCurrentUser(get(), get()) }
    bean { DefaultFirebaseErrorHandler() as FirebaseErrorHandler }
}

val iGymAppModules = listOf(rxModule, viewModule, firebaseDependencies) + RepositoryComponents.execute()