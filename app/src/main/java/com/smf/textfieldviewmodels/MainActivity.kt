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

	Text(text = environment,
		modifier = Modifier.padding(10.dp))

	if (environment == "Local") {
		var localName by rememberSaveable { mutableStateOf("") }   // Local
		TextFieldContent( text = localName,
						  onTextChange = { localName = it })
	} else {
		if (liveDataViewModel != null) {
			val viewModelName by liveDataViewModel.text.observeAsState()   // ViewModel
			TextFieldContent(text = viewModelName.toString(),
							 onTextChange = { liveDataViewModel.onTextChange(it) })
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldContent(text: String, onTextChange: (String) -> Unit) {

	Column(modifier = Modifier.padding(16.dp))
	{
		Spacer(modifier = Modifier.height(50.dp))
		OutlinedTextField(value = text.toString(), onValueChange = onTextChange)
	}
}