package br.com.igym.view.dashboard

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.OnLifecycleEvent
import br.com.igym.base.BaseViewModel
import br.com.igym.data.boundaries.PostRepository
import br.com.igym.domain.di.SchedulerProvider
import br.com.igym.domain.entities.Preference
import br.com.igym.util.FlexibleLiveData
import com.google.firebase.auth.FirebaseUser

class MainViewModel(
        private val firebaseUser: FirebaseUser,
        private val schedulerProvider: SchedulerProvider,
        private val postRepository: PostRepository
) : BaseViewModel() {

    val preferences: LiveData<List<Preference>> get() = preferencesLiveData
    private val preferencesLiveData: FlexibleLiveData<List<Preference>> = FlexibleLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        preferencesLiveData.value = listOf(
                Preference(1, "Aqui vai um pinto"),
                Preference(2, "Cu do gustavo"),
                Preference(3, "Cu no paulinho na reta")
        )
    }
}