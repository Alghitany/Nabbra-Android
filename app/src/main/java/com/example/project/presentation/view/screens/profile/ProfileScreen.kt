package com.example.project.presentation.view.screens.profile

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.project.R
import com.example.project.presentation.view.screens.BottomBar
import com.example.project.presentation.view.screens.chooseEarphone.ChooseEarphone
import com.example.project.presentation.view.screens.homeScreen.HomeScreen
import com.example.project.presentation.view.screens.login.LoginScreen
import com.example.project.ui.theme.DarkSlateBlue
import com.example.project.ui.theme.LightGreen
import com.example.project.ui.theme.MidNightBlue
import com.example.project.ui.theme.inder

class ProfileScreen(private val name: String?, private val email: String?) :Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val activeTab = remember { mutableIntStateOf(0) } // Track active tab

        // Access SharedPreferences to clear the token when logging out
        val context = LocalContext.current
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)


        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomBar(
                    activeTab = activeTab.intValue,
                    //Home Click
                    onHomeClick = {
                        activeTab.intValue = 0
                        navigator.push(HomeScreen(name, email))
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
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Profile",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W700,
                            fontFamily = inder,
                            color = LightGreen,
                            lineHeight = 21.sp,
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Surface(
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(89.dp),
                        color = DarkSlateBlue
                    ){
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 18.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = CircleShape,
                                modifier = Modifier.size(53.dp),
                                border = BorderStroke(2.dp, LightGreen)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.user_image),
                                    contentDescription = "Person Image",
                                    contentScale = ContentScale.Crop,
                                )
                            }

                            Column (
                                modifier = Modifier
                                    .height(35.dp)
                                    .padding(start = 11.dp)
                            ){
                                Text(text = "$name",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.W700,
                                        fontFamily = inder,
                                        color = LightGreen,
                                        lineHeight = 19.sp,
                                    ))
                                
                                Text(text = "$email",
                                    style = TextStyle(
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.W400,
                                        fontFamily = inder,
                                        color = Color.White,
                                        lineHeight = 12.sp,
                                    )
                                )
                            }
                        }

                    }

                    Spacer(modifier = Modifier.height(22.dp))

                    Surface(
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        color = DarkSlateBlue
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp, vertical = 24.dp),
                        ) {
                            CustomRow(
                                icon = R.drawable.profile,
                                mainText = "My Account",
                                subText = "Make changes to your account",
                                onClick = {
                                    // Remove the token from SharedPreferences
                                    sharedPreferences.edit().remove("auth_token").apply()

                                    // Navigate to the Login Screen
                                    navigator.replace(LoginScreen())
                                }
                            )

                            Spacer(modifier = Modifier.height(25.dp))

                            CustomRow(
                                icon = R.drawable.checked,
                                mainText = "Saved Data",
                                subText = "Manage Your Audiometer Data",
                                onClick = {
                                    // Remove the token from SharedPreferences
                                    sharedPreferences.edit().remove("auth_token").apply()

                                    // Navigate to the Login Screen
                                    navigator.replace(LoginScreen())
                                }
                            )

                            Spacer(modifier = Modifier.height(25.dp))

                            CustomRow(icon = R.drawable.logout,
                                mainText = "Log out",
                                subText = "Further secure your account for safety",
                                onClick = {
                                    // Remove the token from SharedPreferences
                                    sharedPreferences.edit().remove("auth_token").apply()

                                    // Navigate to the Login Screen
                                    navigator.replace(LoginScreen())
                                })
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(text = "More",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W500,
                            fontFamily = inder,
                            color = LightGreen,
                            lineHeight = 19.sp,
                        ))
                    Spacer(modifier = Modifier.height(12.dp))

                    Surface(
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(175.dp),
                        color = DarkSlateBlue
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp, vertical = 24.dp),
                        ) {
                            CustomRow(icon = R.drawable.notification,
                                mainText = "Help & Support",
                                subText = "",
                                onClick = {
                                    // Remove the token from SharedPreferences
                                    sharedPreferences.edit().remove("auth_token").apply()

                                    // Navigate to the Login Screen
                                    navigator.replace(LoginScreen())
                                })

                            Spacer(modifier = Modifier.height(25.dp))

                            CustomRow(icon =R.drawable.heart ,
                                mainText = "About App",
                                subText = "",
                                onClick = {
                                    // Remove the token from SharedPreferences
                                    sharedPreferences.edit().remove("auth_token").apply()

                                    // Navigate to the Login Screen
                                    navigator.replace(LoginScreen())
                                })
                        }
                    }

                }
            }
        )
    }
}