package com.example.project.presentation.view.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.R
import com.example.project.ui.theme.LightGreen
import com.example.project.ui.theme.alexandria
import com.example.project.ui.theme.inder

@Composable
fun PageContent1(){
    Column(
        modifier = Modifier.padding(top = 104.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.on_boarding1),
            contentDescription = "Onboarding Image 1",
            modifier = Modifier
                .height(404.dp)
                .width(284.dp)
                .align(Alignment.CenterHorizontally))

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.White,
                        fontWeight = FontWeight.W400,
                        fontSize = 21.sp,
                        fontFamily = inder,
                    )
                ) {
                    append("Welcome to ")
                }
                withStyle(
                    style = SpanStyle(
                        color = LightGreen,
                        fontWeight = FontWeight.W400,
                        fontSize = 21.sp,
                        fontFamily = inder,
                    )
                ) {
                    append("NABBRA – ")
                }
                withStyle(
                    style = SpanStyle(
                        color = LightGreen,
                        fontWeight = FontWeight.W600,
                        fontSize = 18.sp,
                        fontFamily = alexandria,
                    )
                ){
                    append("نـــــــبـــــرة")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.White,
                        fontWeight = FontWeight.W400,
                        fontSize = 21.sp,
                        fontFamily = inder,
                    )
                ){
                    append(" A New Way to Address Hearing Challenges.")
                }
            },
            modifier = Modifier.width(332.dp),
            lineHeight = 31.sp,
            textAlign = TextAlign.Center
        )

    }
}