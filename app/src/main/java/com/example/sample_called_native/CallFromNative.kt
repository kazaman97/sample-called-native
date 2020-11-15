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

    private var countUpThread: Thread? = null

    fun stringFromJNI() = nativeLibrary.stringFromJNI()

    fun startCountUp() {
        if (countUpThread == null) {
            countUpThread = Thread {
                nativeLibrary.startCountUp()
            }.apply {
                start()
            }
        }
    }

    fun stopCountUp() {
        // TODO Threadを破棄する処理が必要
        countUpThread = null
        nativeLibrary.stopCountUp()
    }
}
