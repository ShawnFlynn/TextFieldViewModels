package com.smf.textfieldviewmodels

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.smf.textfieldviewmodels.ui.theme.TextFieldViewModelsTheme

class MainActivity : ComponentActivity() {
	@ExperimentalMaterial3Api
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			TextFieldViewModelsTheme {
				Surface(modifier = Modifier.fillMaxSize())
				{
//					TextFieldScreen()								// Local
					TextFieldScreen(StateFlowViewModel())			// StateFlow
				}
			}
		}
	}
}

@ExperimentalMaterial3Api
@Composable
private fun TextFieldScreen(stateFlowViewModel: StateFlowViewModel? = null)
{
	val environment = if (stateFlowViewModel == null) "Local" else "StateFlow"

	if (environment == "Local") {
		var localText: String by rememberSaveable { mutableStateOf("") }   // Local
		TextFieldContent( text = localText, label = environment) { localText = it }
	} else {
		if (stateFlowViewModel != null) {
			val stateFlowName: String by stateFlowViewModel.text.collectAsState()   // StateFlow
			TextFieldContent( text = stateFlowName, label = environment) { stateFlowViewModel.onTextChange(it) }
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldContent(text: String, label: String, onTextChange: (String) -> Unit) {

	Column(modifier = Modifier.padding(16.dp))
	{
		Text(text = label,
			 modifier = Modifier.padding(10.dp))

		Spacer(modifier = Modifier.height(15.dp))

		OutlinedTextField(value = text, onValueChange = onTextChange)
	}
}
