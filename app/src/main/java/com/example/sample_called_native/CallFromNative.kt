package com.example.sample_called_native

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

    private var count: MutableLiveData<Int>? = null

    fun stringFromJNI() = nativeLibrary.stringFromJNI()

    fun startCountUp(_count: MutableLiveData<Int>) {
        count = _count
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
        count = null
        nativeLibrary.stopCountUp()
    }

    override fun onChangeCount(count: Int) {
        this.count?.postValue(count)
    }
}
