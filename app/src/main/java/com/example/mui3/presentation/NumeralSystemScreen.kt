package com.example.mui3.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun NumberSystemConverter(innerPadding: PaddingValues) {
    var binary by remember { mutableStateOf("1010") }
    var octal by remember { mutableStateOf("12") }
    var decimal by remember { mutableStateOf("10") }
    var hexadecimal by remember { mutableStateOf("A") }

    fun updateFromDecimal(value: String) {
        val dec = value.toIntOrNull() ?: return
        binary = dec.toString(2)
        octal = dec.toString(8)
        decimal = dec.toString(10)
        hexadecimal = dec.toString(16).uppercase()
    }

    fun handleInput(value: String, base: Int) {
        val decValue = try {
            value.toInt(base)
        } catch (e: NumberFormatException) {
            null
        }
        decValue?.let { updateFromDecimal(it.toString()) }
    }

    Column(
        modifier = Modifier.padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        NumericSystemField("Binary", binary, { handleInput(it, 2) })
        NumericSystemField("Octal", octal, { handleInput(it, 8) })
        NumericSystemField("Decimal", decimal, { handleInput(it, 10) })
        NumericSystemField("Hexadecimal", hexadecimal, { handleInput(it, 16) })
    }
}

@Composable
fun NumericSystemField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            textStyle = MaterialTheme.typography.titleLarge.copy(textAlign = TextAlign.Center)
        )
        Spacer(modifier = Modifier.height(4.dp))
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth(0.65f)
        )
    }
}

