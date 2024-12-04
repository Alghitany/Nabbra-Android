package com.example.project.presentation.view.screens.recordingScreen

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.project.R
import com.example.project.presentation.view.reusable.CustomButton
import com.example.project.presentation.view.reusable.CustomIcon
import com.example.project.presentation.view.screens.BottomBar
import com.example.project.presentation.view.screens.chooseEarphone.ChooseEarphone
import com.example.project.presentation.view.screens.profile.ProfileScreen
import com.example.project.presentation.view.screens.ratingScreen.RatingScreen
import com.example.project.ui.theme.LightGreen
import com.example.project.ui.theme.MidNightBlue
import com.example.project.ui.theme.alexandria

class RecordingScreen(private val name: String?, private val email: String?) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val activeTab = remember { mutableIntStateOf(0) }

        val context = LocalContext.current
        val isRecording = remember { mutableStateOf(false) }

        val manager = AudioStreamManager(context)

        val requestPermissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted ->
                if (isGranted) {
                    manager.startStreaming()
                } else {
                    Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        )

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
                        .padding(horizontal = 17.dp)
                ) {
                    // UI Components
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        //BackIcon
                        CustomIcon(onClick = {navigator.pop()})

                        //Help Icon
                        CustomIcon(onClick = {
                            /*TODO*/
                        }, icon = R.drawable.help)

                    }

                    Spacer(modifier = Modifier.height(51.dp))

                    Text(
                        text = "، مرحبا ",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.W400,
                            fontFamily = alexandria,
                            color = Color.White,
                            lineHeight = 14.sp,
                        ),
                        modifier = Modifier.align(Alignment.End)
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(text = "! اهلا بك في نــبـــرة",
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.W400,
                            fontFamily = alexandria,
                            color = LightGreen,
                            lineHeight = 31.sp,
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(78.dp))

                    Text(text = ":خطوة رقم 4",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W400,
                            fontFamily = alexandria,
                            color = LightGreen,
                            lineHeight = 17.sp,
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CustomButton(
                        text =if (isRecording.value) "أوقف التسجيل" else "ابدأ التسجيل",
                        onClick = {
                            if (ActivityCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.RECORD_AUDIO
                                ) == PackageManager.PERMISSION_GRANTED
                            ) {
                                requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                            } else {
                                requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .height(63.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        containerColor = LightGreen,
                        textStyle = TextStyle(
                            fontWeight = FontWeight.W700,
                            fontSize = 20.sp,
                            color = MidNightBlue
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    CustomButton(
                        text = "التالي",
                        onClick = {
                            navigator.push(RatingScreen(name = name,email))
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        contentPadding = ButtonDefaults. ContentPadding,
                        textStyle = TextStyle(
                            color = MidNightBlue,
                            fontFamily = alexandria,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.W400,
                            lineHeight = 22.sp,
                        )
                    )
                }
            }
        )
    }
}
