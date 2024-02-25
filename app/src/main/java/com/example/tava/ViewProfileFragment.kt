package com.example.tava

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tava.databinding.FragmentViewProfileBinding
import com.example.tava.adapter.ProfileAdapter
import com.example.tava.viewmodel.ProfileViewModel

class ViewProfileFragment : Fragment() {
    private var _binding: FragmentViewProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentViewProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        profileViewModel.profilesLiveData.observe(viewLifecycleOwner, { profiles ->
            binding.recyclerViewProfiles.layoutManager = LinearLayoutManager(context)
            binding.recyclerViewProfiles.adapter = ProfileAdapter(profiles) { profile ->
                // Handle profile click
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
