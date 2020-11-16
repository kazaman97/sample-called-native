package com.example.sample_called_native

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sample_called_native.databinding.ActivityMainBinding

internal class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var callFromNative: CallFromNative

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        callFromNative = CallFromNative.newInstance()
        callFromNative.count.observe(this) {
            binding.sec.text = it.toString()
        }

        binding.startButton.setOnClickListener {
            callFromNative.startCountUp()
        }

        binding.stopButton.setOnClickListener {
            callFromNative.stopCountUp()
        }

        // Example of a call to a native method
        binding.sec.text = callFromNative.stringFromJNI()
    }
}
