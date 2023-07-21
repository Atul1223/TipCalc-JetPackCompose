package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.components.InputField
import com.example.tipcalculator.ui.theme.TipCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                PerPersonContribution()
                MainContent()
            }
        }
    }
}

private val headerTextStyle = TextStyle(
    textAlign = TextAlign.Center,
    fontWeight = FontWeight.SemiBold,
    fontSize = 24.sp
)

private val normalTextStyle = TextStyle(
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    color = Color.DarkGray
)

//Creating our own container function:
//Container function is a function which can have another Composable inside it, example of container function:
//Surface, Theme(here TipCalculatorTheme) etc.
@Composable
fun MyApp(content: @Composable () -> Unit) {
    TipCalculatorTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(color = 0xFFF4F2DE)
        ) {
            Column() {
                content()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PerPersonContribution(totalPerPerson: Double = 0.0) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(20.dp)

            //To have rounder corner rectangle kind of shape:
            //.clip(shape = CircleShape.copy(all = CornerSize(15.dp)))
            //.clip(shape = RoundedCornerShape(15.dp))

            //some custom shapes like leaf:
            //.clip(shape = CircleShape.copy(topEnd = CornerSize(15.dp), bottomStart = CornerSize(15.dp)))
            .clip(shape = RoundedCornerShape(topStart = 45.dp, bottomEnd = 45.dp))
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(topStart = 45.dp, bottomEnd = 45.dp)
            ),
        color = Color(color = 0xFFA1CCD1),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(
                text = "Total Per Person",
                style = headerTextStyle
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "$${formatDouble(totalPerPerson)}",
                style = headerTextStyle,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 28.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainContent() {
    BillForm(){

    }

}

private fun formatDouble(value: Double): String {
    return "%.2f".format(value)
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    val totalBillState = remember {
        mutableStateOf("")
    }

    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .padding(20.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(13.dp)),
        color = Color(color = 0xFF7C9D96)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            InputField(valueState = totalBillState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions{
                    if (!validState){
                        return@KeyboardActions
                    }
                    else {
                        onValueChange(totalBillState.value.trim())
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                } )
        }
    }

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipCalculatorTheme {
        MyApp {
            PerPersonContribution()
            MainContent()
        }
    }
}