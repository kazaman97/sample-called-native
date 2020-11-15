package com.example.sample_called_native

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sample_called_native.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val sampleHashMap = HashMap<String, String>()

    private lateinit var binding: ActivityMainBinding

    private lateinit var callFromNative: CallFromNative

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        callFromNative = CallFromNative.newInstance()

//        sampleHashMap["okinawa"] = "naha"
//        sampleHashMap["iwate"] = "morioka"

        binding.stopButton.setOnClickListener {
            callFromNative.stopCountUp()
        }

        callFromNative.startCountUp()

        // Example of a call to a native method
        findViewById<TextView>(R.id.sample_text).text = callFromNative.stringFromJNI()
    }
}
