package com.abilashcse.birthdayapp.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.abilashcse.birthdayapp.data.model.Name

@Composable
fun LoadingUI() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally))
    }
}

@Composable
fun nameChip(name: Name, textSize: Float = 20f) {
    val initial = name.first.first().uppercase() + name.last?.first()?.uppercase()
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Color.DarkGray, shape = CircleShape)
            .layout() { measurable, constraints ->
                // Measure the composable
                val placeable = measurable.measure(constraints)

                //get the current max dimension to assign width=height
                val currentHeight = placeable.height
                var heightCircle = currentHeight
                if (placeable.width > heightCircle)
                    heightCircle = placeable.width

                //assign the dimension and the center position
                layout(heightCircle, heightCircle) {
                    // Where the composable gets placed
                    placeable.placeRelative(0, (heightCircle - currentHeight) / 2)
                }
            }) {

        Text(
            text = initial,
            fontWeight = FontWeight.Bold,
            fontSize = textSize.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .padding(textSize.dp)
                .defaultMinSize(textSize.dp) //Use a min size for short text.
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InitialPreview() {
    val name = Name(title = "Mr", first = "User", last = "One")
    nameChip(name = name, textSize = 40f)
}
