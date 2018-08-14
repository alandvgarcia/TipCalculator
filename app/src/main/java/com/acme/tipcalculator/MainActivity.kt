package com.acme.tipcalculator

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.acme.tipcalculator.databinding.ActivityMainBinding
import com.acme.tipcalculator.viewmodel.CalculatorViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.vm = CalculatorViewModel()

        setSupportActionBar(binding.toolbar)
    }


}
