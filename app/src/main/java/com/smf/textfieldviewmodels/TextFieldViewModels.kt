package com.smf.textfieldviewmodels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StateFlowViewModel {
	private val _stateFlowText = MutableStateFlow<String>("")
	val text: StateFlow<String> = _stateFlowText.asStateFlow()

	fun onNameChange(newText: String) {
		_stateFlowText.value = newText
	}
}
