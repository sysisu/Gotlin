package com.example.tava.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tava.databinding.ItemProfileBinding
import com.example.tava.model.Profile

class ProfileAdapter(
    private val profiles: List<Profile>,
    private val onClick: (Profile) -> Unit
) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    class ProfileViewHolder(private val binding: ItemProfileBinding, val onClick: (Profile) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: Profile) {
            binding.textViewName.text = profile.name
            binding.textViewEmail.text = profile.email
            itemView.setOnClickListener { onClick(profile) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ItemProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(profiles[position])
    }

    override fun getItemCount(): Int = profiles.size
}
