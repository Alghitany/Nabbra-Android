package com.example.project.presentation.view.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.project.ui.theme.DarkSlateBlue


@Composable
fun LoginIconButton(
    image : Int,
) {
    Surface(
        modifier = Modifier
            .size(54.dp),
        shape = CircleShape,
        color = DarkSlateBlue,
        shadowElevation = 18.dp
    ){
        IconButton(
            modifier = Modifier.fillMaxSize(),
            onClick = {
                /*TOD0*/
            }
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.size(30.dp),
            )
        }
    }
}