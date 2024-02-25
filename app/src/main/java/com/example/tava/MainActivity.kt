package com.example.tava

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.content.Intent
import android.nfc.NfcAdapter
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.tava.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var nfcAdapter: NfcAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        setupNavigation()
        setupNFCAdapter()
        setupFab()
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupNFCAdapter() {
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)?.also { adapter ->
            if (!adapter.isEnabled) {
                Snackbar.make(binding.root, R.string.nfc_not_available, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setupFab() {
        binding.fab.apply {
            setOnClickListener {
                // Implement NFC sharing logic here or navigate to a dedicated NFC sharing fragment
                shareProfileViaNFC()
            }
        }
    }

    private fun shareProfileViaNFC() {
        // Check if NFC is enabled and available
        if (nfcAdapter?.isEnabled == true) {
            Snackbar.make(binding.root, R.string.nfc_ready_to_share, Snackbar.LENGTH_LONG).show()
            // Implement the actual NFC sharing logic here
        } else {
            Snackbar.make(binding.root, R.string.enable_nfc_for_sharing, Snackbar.LENGTH_LONG).setAction("Enable") {
                startActivity(Intent(Settings.ACTION_NFC_SETTINGS))
            }.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_create_profile -> { true }
            R.id.action_view_profile -> { true }
            R.id.action_enable_nfc -> { true }
            R.id.action_settings -> { true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment_content_main).navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
