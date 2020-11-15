package com.example.sample_called_native

import com.example.native_library.NativeLibrary

class CallFromNative {
    companion object {
        private var instance: CallFromNative? = null

        fun newInstance() = instance ?: run {
            instance = CallFromNative()
            instance!!
        }
    }

    private val nativeLibrary = NativeLibrary()

    fun stringFromJNI() = nativeLibrary.stringFromJNI()
}
