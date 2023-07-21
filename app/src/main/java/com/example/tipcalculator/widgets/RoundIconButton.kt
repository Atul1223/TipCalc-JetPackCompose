package com.example.tipcalculator.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


val IconButtonModifier = Modifier.size(40.dp)
@Composable
fun RoundIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit,
    tint: Color = Color.Black.copy(alpha = 0.8f),
    backgroundColor: Color = Color(color = 0xFFE9B384),
    elevation: Dp = 4.dp
) {
    Card(
        modifier = modifier
            .size(40.dp)
            .padding(all = 4.dp)
            .clickable {
                onClick.invoke()
            }
            .then(IconButtonModifier),  //chaining a modifier to another modifier
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Icon(
            modifier = Modifier.fillMaxSize(),
            imageVector = imageVector,
            contentDescription = "Plus or Minus Icon",
            tint = tint,
        )
    }
}