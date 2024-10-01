package com.mundo.unitconverterapp.compose.top_screen

import android.icu.text.DecimalFormat
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mundo.unitconverterapp.data.Conversion
import java.math.RoundingMode

@Composable
fun TopScreen(
    list: List<Conversion>,
    selectedConversion: MutableState<Conversion?>,
    inputText: MutableState<String>,
    typedValue: MutableState<String>,
    isLandscape: Boolean,
    save: (String,String) -> Unit
) {
    var toSave by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        ConversionMenu(list = list, isLandscape){
            selectedConversion.value = it
            typedValue.value =  "0.0"
        }

        selectedConversion.value?.let {
            InputBlock(conversion = it, inputText = inputText, isLandscape){input ->
                typedValue.value = input
                toSave = true
            }
        }

        if(typedValue.value != "0.0"){
            val input = typedValue.value.toDouble()
            val multiply = selectedConversion.value?.ratio
            val result = input.times(multiply?:0.0)

            // Rounding the result to 4 decimal places
            val df = DecimalFormat("#.####")
            df.roundingMode = RoundingMode.DOWN.ordinal
            val roundedResult = df.format(result)

            val message1 = "${typedValue.value} ${selectedConversion.value?.convertFrom ?: "unit"} is equal to "
            val message2 = "$roundedResult ${selectedConversion.value?.convertTo ?: "newUnit"}"
            if(toSave){
                save(message1,message2)
                toSave = false
            }

            ResultBlock(message1 = message1, message2 = message2)
        }
    }

}