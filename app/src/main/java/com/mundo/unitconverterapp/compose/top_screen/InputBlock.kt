package com.mundo.unitconverterapp.compose.top_screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mundo.unitconverterapp.data.Conversion

@Composable
fun InputBlock(
    conversion: Conversion,
    inputText: MutableState<String>,
    isLandscape: Boolean,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    calculate: (String) -> Unit
){
    if(isLandscape){
        Column(modifier.padding(top = 20.dp)) {
            Row (){
                TextField(
                    value = inputText.value,
                    onValueChange = {
                        inputText.value = it
                    },
                    modifier = modifier,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = true,
                        keyboardType = KeyboardType.Number
                    ),
                    colors = TextFieldDefaults.colors(unfocusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.3F)),
                    textStyle = TextStyle(color = Color.DarkGray, fontSize = 30.sp)
                )
                Text(
                    text = conversion.convertFrom,
                    fontSize = 24.sp,
                    modifier = modifier
                        .padding(start = 10.dp, top = 30.dp)
                )
            }

            Spacer(modifier = modifier.height(20.dp))

            OutlinedButton(
                onClick = {
                    if(inputText.value != "") {
                        calculate(inputText.value)
                    } else {
                        Toast.makeText(context,"Input value cannot be empty", Toast.LENGTH_LONG).show()
                    }
                },
            ) {
                Text(
                    text = "Convert",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )
            }
        }
    } else {
        Column(modifier.padding(top = 20.dp)) {
            Row (modifier.fillMaxWidth()){
                TextField(
                    value = inputText.value,
                    onValueChange = {
                        inputText.value = it
                    },
                    modifier = modifier.fillMaxWidth(0.65F),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = true,
                        keyboardType = KeyboardType.Number
                    ),
                    colors = TextFieldDefaults.colors(unfocusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.3F)),
                    textStyle = TextStyle(color = Color.DarkGray, fontSize = 30.sp)
                )
                Text(
                    text = conversion.convertFrom,
                    fontSize = 24.sp,
                    modifier = modifier
                        .padding(start = 10.dp, top = 30.dp)
                        .fillMaxWidth(0.35F)
                )
            }

            Spacer(modifier = modifier.height(20.dp))

            OutlinedButton(
                onClick = {
                    if(inputText.value != "") {
                        calculate(inputText.value)
                    } else {
                        Toast.makeText(context,"Input value cannot be empty", Toast.LENGTH_LONG).show()
                    }
                },
                modifier = modifier.fillMaxWidth(1F)
            ) {
                Text(
                    text = "Convert",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )
            }
        }
    }
}