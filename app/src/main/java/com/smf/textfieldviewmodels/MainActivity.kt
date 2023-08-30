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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.smf.textfieldviewmodels.ui.theme.TextFieldViewModelsTheme

class MainActivity : ComponentActivity() {
	@OptIn(ExperimentalMaterial3Api::class)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			TextFieldViewModelsTheme {
				Surface(modifier = Modifier.fillMaxSize())
				{
					TextFieldScreen()						// Local
				}
			}
		}
	}
}

@ExperimentalMaterial3Api
@Composable
private fun TextFieldScreen()
{
	val environment = "Local"

	Text(text = environment,
		modifier = Modifier.padding(10.dp))

		var localText: String by rememberSaveable { mutableStateOf("") }   // Local
		TextFieldContent(text = localText) { localText = it }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldContent(text: String, onTextChange: (String) -> Unit) {

	Column(modifier = Modifier.padding(16.dp))
	{
		Spacer(modifier = Modifier.height(50.dp))
		OutlinedTextField(value = text, onValueChange = onTextChange)
	}
}