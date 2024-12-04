package com.example.project.presentation.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.project.R
import com.example.project.ui.theme.DarkSlateBlue
import com.example.project.ui.theme.MidNightBlue

@Composable
fun BottomBar(
    activeTab: Int,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        color = DarkSlateBlue,
        shape = RoundedCornerShape(topStart = 34.dp, topEnd = 34.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Settings Icon (Active if activeTab == 1)
            IconButton(onClick = { onSettingsClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = "Settings",
                    tint = if (activeTab == 1) Color.White else MidNightBlue,
                    modifier = Modifier
                        .size(40.dp)
                )
            }

            // Home Icon (Active if activeTab == 0)
            IconButton(onClick = { onHomeClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home",
                    tint = if (activeTab == 0) Color.White else MidNightBlue,
                    modifier = Modifier
                        .offset(y = (-8).dp)
                        .size(50.dp)
                )
            }

            // Profile Icon (Active if activeTab == 2)
            IconButton(onClick = { onProfileClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.person),
                    contentDescription = "Person",
                    tint = if (activeTab == 2) Color.White else MidNightBlue,
                    modifier = Modifier
                        .size(40.dp)
                )
            }
        }
    }
}
