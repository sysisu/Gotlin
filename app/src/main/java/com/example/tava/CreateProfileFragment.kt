package com.example.tava

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tava.databinding.FragmentCreateProfileBinding
import android.util.Patterns

class CreateProfileFragment : Fragment() {

    private var _binding: FragmentCreateProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding.buttonSave.setOnClickListener {
            if (validateInput()) {
                saveProfile()
            }
        }
    }

    private fun validateInput(): Boolean {
        val name = binding.editTextName.text.toString().trim()
        val email = binding.editTextEmail.text.toString().trim()
        val linkedIn = binding.editTextLinkedIn.text.toString().trim()

        // Validate Name
        if (name.isEmpty()) {
            binding.editTextName.error = getString(R.string.name_required)
            return false
        }

        // Validate Email
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editTextEmail.error = getString(R.string.invalid_email)
            return false
        }

        // Validate LinkedIn URL (basic check)
        if (linkedIn.isEmpty() || !Patterns.WEB_URL.matcher(linkedIn).matches()) {
            binding.editTextLinkedIn.error = getString(R.string.invalid_linkedin_url)
            return false
        }

        return true
    }

    private fun saveProfile() {
        // TODO: Implement actual data storage logic, either locally or through a cloud database

        // Show a success message
        Toast.makeText(context, getString(R.string.profile_saved_success), Toast.LENGTH_SHORT).show()

        // Optionally navigate back to the main activity or another relevant screen
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
