package com.acme.tipcalculator.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.acme.tipcalculator.R
import com.acme.tipcalculator.databinding.ActivityMainBinding
import com.acme.tipcalculator.viewmodel.CalculatorViewModel

class MainActivity : AppCompatActivity(), SaveDialogFragment.Callback {
    override fun onSaveTip(name: String) {
        Snackbar.make(binding.root, "Saved $name", Snackbar.LENGTH_SHORT).show()
    }

    lateinit var binding: ActivityMainBinding


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tip_calculator, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                showSaveDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSaveDialog() {
        val saveDialogFragment = SaveDialogFragment()
        saveDialogFragment.show(supportFragmentManager, "SaveDialog")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        setSupportActionBar(binding.toolbar)
    }


}
