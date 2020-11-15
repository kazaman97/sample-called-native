package com.example.sample_called_native

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.native_library.NativeLibrary

class CallFromNative : NativeLibrary.Listener {
    companion object {
        private var instance: CallFromNative? = null

        fun newInstance() = instance ?: run {
            instance = CallFromNative()
            instance!!
        }
    }

    private val nativeLibrary = NativeLibrary()

    private var countUpThread: Thread? = null

    private val mutableLiveData = MutableLiveData<Int>()

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

    fun setListener(activity: Activity): LiveData<Int> {
        NativeLibrary.setListener(activity)
        return mutableLiveData
    }

    override fun onChangeCount(count: Int) {
        Log.d("CALL", "呼ばれている")
        mutableLiveData.value = count
    }
}
