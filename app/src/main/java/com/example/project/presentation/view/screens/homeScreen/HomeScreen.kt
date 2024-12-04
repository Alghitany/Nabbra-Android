package com.example.project.presentation.view.screens.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.project.R
import com.example.project.presentation.view.screens.BottomBar
import com.example.project.presentation.view.screens.chooseEarphone.ChooseEarphone
import com.example.project.presentation.view.screens.profile.ProfileScreen
import com.example.project.ui.theme.DarkSlateBlue
import com.example.project.ui.theme.LightGreen
import com.example.project.ui.theme.MidNightBlue
import com.example.project.ui.theme.WhiteOpacity20
import com.example.project.ui.theme.alexandria
import com.example.project.ui.theme.inder

class HomeScreen(private val name: String?,private val  email: String?) :Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val activeTab = remember { mutableIntStateOf(0) } // Track active tab
        var selectedIndex by remember { mutableIntStateOf(1) } // -1 means no selection


        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomBar(
                    activeTab = activeTab.intValue,
                    //Home Click
                    onHomeClick = {
                        activeTab.intValue = 0
                    },
                    //Settings Click
                    onSettingsClick = {
                        activeTab.intValue = 1
                        navigator.push(ChooseEarphone(name,email))
                    },
                    //Profile Click
                    onProfileClick = {
                        activeTab.intValue = 2
                        navigator.push(ProfileScreen(name,email))
                    }
                )
            },
            content = { contentPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MidNightBlue)
                        .padding(contentPadding)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Home",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W700,
                                fontFamily = inder,
                                color = LightGreen,
                                lineHeight = 21.sp,
                            ))
                        Icon(
                            painter = painterResource(id = R.drawable.help),
                            contentDescription = "Help",
                            tint = LightGreen,
                            modifier = Modifier
                                .size(height = 33.dp, width = 29.dp)
                                .clickable {
                                    /*TODO*/
                                }
                        )
                    }

                    Spacer(modifier = Modifier.height(80.dp))

                    Text(text = "L'AVVENTO (HP11B)",
                        style = TextStyle(
                            fontSize = 23.sp,
                            fontWeight = FontWeight.W400,
                            fontFamily = alexandria,
                            color = LightGreen,
                            lineHeight = 26.sp,
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )


                    Surface(
                        shape = CircleShape,
                        color = LightGreen,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(height = 275.dp, width = 275.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.white_nigga),
                            contentDescription = "Earphone Image",
                            contentScale = ContentScale.Crop,
                        )
                    }

                    Icon(painter = painterResource(id = R.drawable.battery),
                        contentDescription ="Battery",
                        modifier = Modifier
                            .rotate(90f)
                            .align(Alignment.CenterHorizontally),
                        tint = Color.White)

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth() // Ensure the Row takes the full width
                            .background(Color.Black.copy(alpha = .2f))
                            .padding(horizontal = 17.dp)
                            .height(205.dp)
                            .align(Alignment.CenterHorizontally),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(text = "التحكم في الضوضاء(قريبا)",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W400,
                                fontFamily = alexandria,
                                color = LightGreen,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.End
                            ),
                            modifier = Modifier
                                .align(Alignment.End)
                                .padding(top = 13.dp))

                        Spacer(modifier = Modifier.height(24.dp))

                        Surface(
                            modifier = Modifier
                                .height(45.dp)
                                .width(280.dp),
                            shape = RoundedCornerShape(10.dp),
                            color = WhiteOpacity20
                        ) {
                            Row(modifier = Modifier.fillMaxSize()) {
                                // Icon 1
                                Icon(
                                    painter = painterResource(id = R.drawable.person1),
                                    contentDescription = "Person 1",
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxSize()
                                        .background(if (selectedIndex == 0) WhiteOpacity20 else DarkSlateBlue) // Change color based on selection
                                        .clickable {
                                            selectedIndex =
                                                if (selectedIndex == 0) -1 else 0 // Toggle selection
                                        },
                                    tint = LightGreen,
                                )

                                Icon(
                                    painter = painterResource(id = R.drawable.person),
                                    contentDescription = "Person 2",
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxSize()
                                        .background(if (selectedIndex == 1) WhiteOpacity20 else DarkSlateBlue) // Change color based on selection
                                        .clickable {
                                            selectedIndex =
                                                if (selectedIndex == 1) -1 else 1 // Toggle selection
                                        },
                                    tint = LightGreen,
                                )

                                // Icon 3
                                Icon(
                                    painter = painterResource(id = R.drawable.person1),
                                    contentDescription = "Person 3",
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxSize()
                                        .background(if (selectedIndex == 2) WhiteOpacity20 else DarkSlateBlue) // Change color based on selection
                                        .clickable {
                                            selectedIndex =
                                                if (selectedIndex == 2) -1 else 2 // Toggle selection
                                            //navigator.push()
                                        },
                                    tint = LightGreen,
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(59.dp))
                        Row (modifier = Modifier.clickable {
                            /*TODO*/
                        }
                            .height(22.dp)
                            .background(Color.Transparent)
                            .align(Alignment.End)){
                            Icon(painter = painterResource(id = R.drawable.left_arrow),
                                contentDescription ="Left arrow",
                                tint = LightGreen)
                            Text(text = "الذهاب الى وحدة التحكم الكاملة",
                                style = TextStyle(
                                    fontFamily = alexandria,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.W400,
                                    color = LightGreen,
                                    lineHeight = 20.sp,
                                    textAlign = TextAlign.End
                                ))
                        }
                    }
                }
            }
        )
    }
}