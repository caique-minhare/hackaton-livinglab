package br.com.igym.view.dashboard

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import br.com.igym.domain.entities.Preference

class PreferencesAdapter : RecyclerView.Adapter<PreferenceViewHolder>() {

    private val preferenceList: MutableList<Preference> = mutableListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = PreferenceViewHolder.inflate(viewGroup)

    override fun getItemCount() = preferenceList.size

    override fun onBindViewHolder(
            preferenceViewHolder: PreferenceViewHolder, position: Int
    ) = preferenceViewHolder.setPreference(preferenceList[position])

    fun setPreferences(preferences: List<Preference>) {
        preferenceList.addAll(preferences)
        notifyDataSetChanged()
    }
}