package com.example.native_library

import android.app.Activity
import android.util.Log

class NativeLibrary() {
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String
    external fun startCountUp()
    external fun stopCountUp()

    companion object {
        interface Listener {
            fun onChangeCount(count: Int)
        }

        private var listener: Listener? = null

        fun setListener(listener: Listener) {
            this.listener = listener
        }

        @JvmStatic
        fun setCount(count: Int) {
            Log.d("NATIVE", count.toString())
            listener?.onChangeCount(count)
        }

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
