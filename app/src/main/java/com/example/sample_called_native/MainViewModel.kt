package com.example.sample_called_native

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal class MainViewModel : ViewModel() {
    private val callFromNative = CallFromNative.newInstance()

    private val _count: MutableLiveData<Int> = MutableLiveData()
    val count: LiveData<Int>? = _count

    fun startCountUp() {
        callFromNative.startCountUp(_count)
    }

    fun stopCountUp() {
        callFromNative.stopCountUp()
    }
}
