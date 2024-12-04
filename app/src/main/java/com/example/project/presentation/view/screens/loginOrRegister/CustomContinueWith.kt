package com.example.project.presentation.view.screens.loginOrRegister

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.ui.theme.DarkSlateBlue
import com.example.project.ui.theme.GainsBoro
import com.example.project.ui.theme.centuryGothic


@Composable
fun CustomContinueWith(image:Int){
    Button(
        onClick = {
            /*TODO*/ },
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkSlateBlue,
        ),
        border = BorderStroke(0.8.dp, GainsBoro),
        contentPadding = PaddingValues(0.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Image(
                painter = painterResource(id = image) ,
                contentDescription = "Google Logo",
                modifier = Modifier.size(height = 33.dp, width = 29.dp)
            )
            Spacer(modifier = Modifier.size(7.dp))
            Text(
                text = "Continue with Google",
                style = TextStyle(
                    fontFamily = centuryGothic,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700,
                    lineHeight = 17.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            )

        }
    }
}