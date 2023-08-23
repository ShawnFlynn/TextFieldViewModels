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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.smf.textfieldviewmodels.ui.theme.TextFieldViewModelsTheme

class MainActivity : ComponentActivity() {
	@OptIn(ExperimentalMaterial3Api::class)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			TextFieldViewModelsTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
//					TextFieldScreen()							// Local
					TextFieldScreen(LiveDataViewModel())		// LiveData
				}
			}
		}
	}
}

@ExperimentalMaterial3Api
@Composable
private fun TextFieldScreen(liveDataViewModel: LiveDataViewModel? = null)
{
	val environment = if (liveDataViewModel == null) "Local" else "LiveData"

	if (environment == "Local") {
		var localText: String by rememberSaveable { mutableStateOf("") }   // Local
		TextFieldContent( text = localText,
						  label = environment)
						 { localText = it }
	} else {
		if (liveDataViewModel != null) {
			val viewModelText: String? by liveDataViewModel.text.observeAsState()  // LiveData
			TextFieldContent(text = viewModelText.toString(),
							 label = environment)
							{ liveDataViewModel.onTextChange(it) }
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