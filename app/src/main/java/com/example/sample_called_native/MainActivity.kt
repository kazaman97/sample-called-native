package com.example.sample_called_native

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val sampleHashMap = HashMap<String, String>()

    private lateinit var callFromNative: CallFromNative

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callFromNative = CallFromNative.newInstance()

        sampleHashMap["okinawa"] = "naha"
        sampleHashMap["iwate"] = "morioka"

        // Example of a call to a native method
        findViewById<TextView>(R.id.sample_text).text = callFromNative.stringFromJNI()
    }
}
