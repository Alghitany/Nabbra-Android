package com.example.project.presentation.view.screens.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.R
import com.example.project.ui.theme.DarkGrey
import com.example.project.ui.theme.LightGreen
import com.example.project.ui.theme.MidNightBlue
import com.example.project.ui.theme.inder

@Composable
fun CustomRow(
    icon: Int,
    mainText: String,
    subText: String,
    onClick: () -> Unit = {},
              ){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable { onClick() }
    ) {
        Surface(
            modifier = Modifier
                .size(50.dp),
            shape = CircleShape,
            color = MidNightBlue.copy(alpha = .3f),
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Icon",
                modifier = Modifier.padding(10.dp),
                tint = LightGreen
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.height(40.dp)
        ) {
            Text(text = mainText,
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.W500,
                    fontFamily = inder,
                    color = LightGreen,
                    lineHeight = 19.sp,
                )
            )
            Text(text = subText,
                style = TextStyle(
                    fontSize = 11.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = inder,
                    color = DarkGrey,
                    lineHeight = 12.sp,
                )
            )
        }

        Spacer(modifier = Modifier.width(44.dp))

        if (mainText == "My Account"){
        Icon(
            painter = painterResource(id = R.drawable.warning),
            contentDescription = "Warning Icon",
            tint = Color.Red)}

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            painter = painterResource(id = R.drawable.arrow_forward),
            contentDescription = "Arrow Forward",
            tint = LightGreen,)


    }
}