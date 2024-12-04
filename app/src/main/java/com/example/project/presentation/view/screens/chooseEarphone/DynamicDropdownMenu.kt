package com.example.project.presentation.view.screens.chooseEarphone

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.R
import com.example.project.ui.theme.DarkSeaGreen
import com.example.project.ui.theme.DarkSlateBlue
import com.example.project.ui.theme.LightGray
import com.example.project.ui.theme.LightGreen
import com.example.project.ui.theme.WhiteOpacity20
import com.example.project.ui.theme.alexandria

@Composable
fun DynamicDropdownMenu(
    menuItems: List<String>, // List of menu options
    onItemSelected: (String) -> Unit // Callback to handle item selection
) {
    val expanded = remember { mutableStateOf(false) } // Controls menu visibility
    val selectedOption = remember { mutableStateOf("") } // Tracks the selected option

    Box(
        modifier = Modifier
            .fillMaxWidth(), // Make the box fill the entire screen
        contentAlignment = Alignment.Center // Center the content
    ) {
        // Box with the OutlinedTextField
        Box(
            modifier = Modifier
                .width(287.dp)
                .height(59.dp)
                .border(
                    width = 2.dp, // Custom border width
                    color = LightGray, // Border color
                    shape = RoundedCornerShape(10.dp) // Same shape as the text field
                )
        ) {
            OutlinedTextField(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                value = selectedOption.value,
                onValueChange = { },
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = LightGreen,
                    focusedContainerColor = DarkSlateBlue,
                    unfocusedContainerColor = DarkSlateBlue,
                    focusedBorderColor = Color.Transparent, // Hide default border
                    unfocusedBorderColor = Color.Transparent, // Hide default border
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_down),
                        contentDescription = "Drop Down icon",
                        tint = DarkSeaGreen,
                        modifier = Modifier
                            .size(26.dp)
                            .clickable { expanded.value = !expanded.value } // Toggle dropdown visibility
                    )
                },
                placeholder = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = "اختر نوع سماعتك",
                            style = TextStyle(
                                color = WhiteOpacity20,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W400,
                                fontFamily = alexandria,
                                lineHeight = 16.5.sp,
                            ),
                        )

                    }
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = alexandria,
                    lineHeight = 16.5.sp,
                    textAlign = TextAlign.End
                ),
                singleLine = true
            )
        }

        // Dropdown Menu
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }, // Close the menu when clicking outside
            modifier = Modifier
                .width(287.dp) // Same width as the text field
        ) {
            menuItems.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        selectedOption.value = item // Update the selected option
                        onItemSelected(item) // Pass the selected option back to the parent
                        expanded.value = false // Close the dropdown menu
                    }
                )
            }
        }
    }
}

