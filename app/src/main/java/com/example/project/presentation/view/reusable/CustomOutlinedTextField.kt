package com.example.project.presentation.view.reusable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.ui.theme.DarkSlateBlue
import com.example.project.ui.theme.LightGray
import com.example.project.ui.theme.LightGreen
import com.example.project.ui.theme.WhiteOpacity20
import com.example.project.ui.theme.centuryGothic

@Composable
fun CustomOutlinedTextField(
    placeholderText:String,
    value:String,
    onValueChange:(String)-> Unit,
    leadingIcon: Int,
    visualTransformation: VisualTransformation = VisualTransformation. None,
    trailingIcon: @Composable (() -> Unit)? = null,
    )
{
    return OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = LightGreen,
            focusedContainerColor = DarkSlateBlue,
            unfocusedContainerColor = DarkSlateBlue,
            focusedBorderColor = LightGray,
            unfocusedBorderColor = LightGray,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        ),
        placeholder = {
            Text(
                text = placeholderText,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = centuryGothic,
                    lineHeight = 17.sp,
                )
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = leadingIcon),
                contentDescription = "icon",
                tint = WhiteOpacity20
            )
        },
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon
        )
}