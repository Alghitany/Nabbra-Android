package com.example.project.presentation.view.reusable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.ui.theme.LightGray
import com.example.project.ui.theme.MidNightBlue
import com.example.project.ui.theme.inder

@Composable
fun CustomButton(
    modifier: Modifier = Modifier
        .height(34.dp)
        .width(115.dp),
    text: String,
    onClick: () -> Unit,
    containerColor: Color = LightGray,
    contentColor: Color = MidNightBlue,
    shape: Shape = RoundedCornerShape(34.dp),
    textStyle: TextStyle = TextStyle(
        fontSize = 17.sp,
        fontFamily = inder,
        fontWeight = FontWeight.W400,
        lineHeight = 22.sp,
        textAlign = TextAlign.Center
    ),
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = shape,
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        Text(
            text = text,
            style = textStyle
        )
    }
}
