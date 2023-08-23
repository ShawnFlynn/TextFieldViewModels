package com.smf.textfieldviewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


// LiveData
class LiveDataViewModel() {
	private var _liveDataText = MutableLiveData<String>("")
	val text: LiveData<String> = _liveDataText

	fun onTextChange(newName: String) {
		_liveDataText.value = newName
	}
}
// implementation("androidx.compose.runtime:runtime-livedata:1.5.0") required in app:build.gradle.kts
