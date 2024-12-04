package com.example.project.presentation.view.screens.ratingScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.ui.theme.alexandria

@Composable
fun StarRatingBarRTL(
    maxStars: Int = 5,
    rating: Float,
    onRatingChanged: (Float) -> Unit,
    starSize: Float = 45f // Default size in dp
) {
    val starSpacing = 8.dp // Spacing between stars
    val arabicNumbers = listOf("١", "٢", "٣", "٤", "٥").reversed() // Reverse for RTL layout

    Row(
        modifier = Modifier.selectableGroup(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxStars) {
            val isSelected = (maxStars - i + 1) <= rating // Logic for RTL selection
            val iconTintColor = if (isSelected) Color(0xFFFFC700) else Color.Gray // Selected: Yellow, Unselected: Gray

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .selectable(
                        selected = isSelected,
                        onClick = {
                            onRatingChanged((maxStars - i + 1).toFloat()) // Adjust index for RTL
                        }
                    )
                    .width(starSize.dp)
            ) {
                // Star Icon
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = iconTintColor,
                    modifier = Modifier
                        .height(starSize.dp)
                        .width(starSize.dp)
                )

                // Arabic Number Underneath the Star
                Text(
                    text = arabicNumbers[i - 1], // Reversed index for RTL
                    style = TextStyle(
                        fontFamily = alexandria,
                        fontWeight = FontWeight.W400,
                        fontSize = 15.sp,
                        lineHeight = 21.sp,
                        color = Color.White
                    )
                )
            }

            if (i < maxStars) {
                Spacer(modifier = Modifier.width(starSpacing))
            }
        }
    }
}

@Composable
fun StarRatingSampleRTL() {
    var rating by remember { mutableFloatStateOf(0f) } // Default rating is 0 (no stars selected)

    StarRatingBarRTL(
        maxStars = 5,
        rating = rating,
        starSize = 45f, // You can pass a dynamic value here
        onRatingChanged = {
            rating = it
        }
    )
}
