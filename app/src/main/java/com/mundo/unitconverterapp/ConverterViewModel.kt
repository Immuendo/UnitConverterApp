package com.mundo.unitconverterapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mundo.unitconverterapp.data.Conversion
import com.mundo.unitconverterapp.data.ConversionResult
import com.mundo.unitconverterapp.data.ConverterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConverterViewModel(
    private val repository: ConverterRepository
): ViewModel() {

    val selectedConversion: MutableState<Conversion?> = mutableStateOf(null)
    val inputText: MutableState<String> = mutableStateOf("")
    val typedValue = mutableStateOf("0.0")

    fun getConversions() = listOf(
        Conversion(1, "Pounds to Kilograms", "lbs", "kg", 0.453592),
        Conversion(2, "Kilograms to Pounds", "kg", "lbs", 2.20462),
        Conversion(3, "Yards to Meters", "yd", "m", 0.9144),
        Conversion(4, "Meters to Yards", "m", "yd", 1.09361),
        Conversion(5, "Miles to Kilometers", "mi", "km", 1.60934),
        Conversion(6, "Kilometers to Miles", "km", "mi", 0.621371),
        Conversion(7, "Grams to Ounces", "g", "oz", 0.035274),
        Conversion(8, "Ounces to Grams", "oz", "g", 28.3495),
        Conversion(9, "Cups to Gallons", "c", "gal", 0.0625),
        Conversion(10, "Gallons to Cups", "gal", "c", 16.00)
    )

    val resultList = repository.getSavedResults()

    fun addResult(message1: String, message2: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertResult(
                ConversionResult(0, message1, message2)
            )
        }
    }

    fun deleteResult(result: ConversionResult){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteResult(result)
        }
    }

    fun deleteAllResults(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllResults()
        }
    }

}