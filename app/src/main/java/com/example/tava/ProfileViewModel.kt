package com.example.tava.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tava.model.Profile

class ProfileViewModel : ViewModel() {
    private val _profilesLiveData = MutableLiveData<List<Profile>>()
    val profilesLiveData: LiveData<List<Profile>> = _profilesLiveData

    init {
        // Example initialization
        // Populate _profilesLiveData with data here or in a function call
        _profilesLiveData.value = listOf(
            // Example Profile data
            Profile(id = 1, name = "John Doe", email = "john.doe@example.com"),
            Profile(id = 2, name = "Jane Doe", email = "jane.doe@example.com")
            // Add more profiles as needed
        )
    }

    // Add any additional functions to manipulate profiles data if necessary
}
