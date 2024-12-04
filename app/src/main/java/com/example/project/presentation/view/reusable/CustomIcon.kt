package com.example.project.presentation.view.reusable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.project.R
import com.example.project.ui.theme.LightGreen

@Composable
fun CustomIcon(
                icon : Int = R.drawable.arrow_back,
               onClick: () -> Unit,
               ) {
    Icon(
        painter = painterResource(id = icon),
        contentDescription = "Arrow Back",
        tint = LightGreen,
        modifier = Modifier
            .size(height = 26.dp, width = 24.dp)
            .clickable {
                onClick()
            }
    )
}