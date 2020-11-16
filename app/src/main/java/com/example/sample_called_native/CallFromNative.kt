package com.example.sample_called_native

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.native_library.NativeLibrary

internal class CallFromNative : NativeLibrary.Companion.Listener {
    companion object {
        private var instance: CallFromNative? = null

        fun newInstance() = instance ?: CallFromNative().also {
            instance = it
        }
    }

    init {
        NativeLibrary.setListener(this)
    }

    private val nativeLibrary = NativeLibrary()

    private var countUpThread: Thread? = null

    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int> = _count

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
        countUpThread = null
        nativeLibrary.stopCountUp()
    }

    override fun onChangeCount(count: Int) {
        Log.d("CALL", "呼ばれている")
        _count.postValue(count)
    }
}
