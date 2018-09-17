package br.com.igym.view.dashboard

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import br.com.igym.R
import br.com.igym.databinding.ActivityDashboardBinding
import com.google.firebase.auth.FirebaseUser
import br.com.igym.domain.entities.Preference
import br.com.igym.util.FIREBASE_USER
import br.com.igym.util.observe
import br.com.igym.util.view.Arguments.ARG_USER
import br.com.igym.util.view.argument
import org.koin.android.architecture.ext.viewModel
import org.koin.standalone.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent {

    private val user: FirebaseUser by argument(ARG_USER)
    private val mainViewModel: MainViewModel by viewModel { mapOf(FIREBASE_USER to user) }
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var adapter: PreferencesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        setupRecyclerView()
        lifecycle.addObserver(mainViewModel)
        observerUi()
    }

    private fun observerUi() {
        mainViewModel.preferences.observe(this, ::onGetPreferenceList)
    }

    private fun setupRecyclerView() {
        adapter = PreferencesAdapter()
//        binding.preferencesRecyclerView.adapter = adapter
        val gridLayoutManger = GridLayoutManager(this, 3)
//        binding.preferencesRecyclerView.layoutManager = gridLayoutManger
    }

    private fun onGetPreferenceList(preferences: List<Preference>?) {
        preferences?.let {
            adapter.setPreferences(it)
        }
    }

}