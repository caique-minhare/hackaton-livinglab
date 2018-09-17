package br.com.igym.view.dashboard

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.igym.databinding.ItemPreferenceBinding
import br.com.igym.domain.entities.Preference

class PreferenceViewHolder(private val binding: ItemPreferenceBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun inflate(parent: ViewGroup?) = PreferenceViewHolder(ItemPreferenceBinding.inflate(LayoutInflater.from(parent?.context), parent, false))
    }

    fun setPreference(preference: Preference) {
        binding.preferenceDescription.text = preference.description
    }
}