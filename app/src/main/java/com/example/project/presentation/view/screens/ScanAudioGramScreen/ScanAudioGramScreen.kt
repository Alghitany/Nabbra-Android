package com.example.project.presentation.view.screens.ScanAudioGramScreen

import android.Manifest
import android.graphics.Bitmap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
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
import com.example.project.presentation.view.screens.recordingScreen.RecordingScreen
import com.example.project.ui.theme.LightGray
import com.example.project.ui.theme.LightGreen
import com.example.project.ui.theme.MidNightBlue
import com.example.project.ui.theme.alexandria

import androidx.compose.ui.Alignment
import com.example.project.presentation.view.reusable.CustomIcon
import com.example.project.presentation.view.screens.goToSettingsScreen.GoToSettingsScreen
import com.example.project.presentation.view.screens.profile.ProfileScreen
import kotlinx.coroutines.delay

class ScanAudioGramScreen(private val name: String?,private val email : String?) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val activeTab = remember { mutableIntStateOf(0) }
        val context = LocalContext.current

        // Bitmap state to hold the captured photo
        var capturedImage by remember { mutableStateOf<Bitmap?>(null) }
        var showPermissionDeniedToast by remember { mutableStateOf(false) }

        // State for showing progress indicator
        var isProgressVisible by remember { mutableStateOf(false) }

        // Camera launcher to capture the image
        val cameraLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview()
        ) { bitmap -> capturedImage = bitmap }

        // Permission launcher for requesting camera permission
        val permissionLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                cameraLauncher.launch() // Launch the camera if permission is granted
            } else {
                showPermissionDeniedToast = true // Trigger a toast message
            }
        }

        // Handle permission denial toast
        LaunchedEffect(showPermissionDeniedToast) {
            if (showPermissionDeniedToast) {
                Toast.makeText(
                    context,
                    "Camera permission is required to use this feature.",
                    Toast.LENGTH_SHORT
                ).show()
                showPermissionDeniedToast = false // Reset the flag
            }
        }

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
                        navigator.push(GoToSettingsScreen(name,email))
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        //BackIcon
                        CustomIcon(onClick = {navigator.pop()})

                        //Help Icon
                        CustomIcon(onClick = {/*TODO*/}, icon = R.drawable.help)

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

                    Text(
                        text = "! اهلا بك في نــبـــرة",
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.W400,
                            fontFamily = alexandria,
                            color = LightGreen,
                            lineHeight = 31.sp,
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    Text(
                        text = ":خطوة رقم 3",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W400,
                            fontFamily = alexandria,
                            color = Color.White,
                            lineHeight = 17.sp,
                        ),
                        modifier = Modifier.align(Alignment.End)
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    Text(
                        text = """
        من فضلك، قم بالضغط على 
        الأيقونة بالأسفل ثم وضع معلومات
        المقياس السمعي حتى نقوم
        بتعديل السماعة حتى تناسب
        !سمعك
    """.trimIndent(),
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W400,
                            fontFamily = alexandria,
                            color = Color.White,
                            lineHeight = 21.sp,
                            textAlign = TextAlign.End
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(268.dp)
                    )



                    Spacer(modifier = Modifier.height(45.dp))
                    Row {

                        Box(
                            modifier = Modifier.size(200.dp)
                        ){
                            capturedImage?.let { bitmap ->
                                Image(
                                    bitmap = bitmap.asImageBitmap(),
                                    contentDescription = "Captured Photo",
                                    modifier = Modifier
                                        .size(150.dp)
                                )
                            }
                        }
                        Column (
                            verticalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.height(50.dp)
                        ){
                            Button(
                                onClick = {
                                    isProgressVisible = true
                                },
                                shape = RoundedCornerShape(38.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = LightGray,
                                ),
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            ) {
                                Text(
                                    text = "التالي",
                                    style = TextStyle(
                                        color = MidNightBlue,
                                        fontFamily = alexandria,
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.W400,
                                        lineHeight = 22.sp,
                                    )
                                )
                            }

                            if (isProgressVisible) {
                                LaunchedEffect(Unit) {
                                    delay(5000)
                                    navigator.push(RecordingScreen(name = name,email))
                                }
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    color = LightGreen
                                )
                            }
                        }


                    }

                    Button(
                        onClick = {
                            permissionLauncher.launch(Manifest.permission.CAMERA)
                        },
                        shape = RoundedCornerShape(38.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(200.dp)
                    )
                    {
                        Image(
                            painter = painterResource(id = R.drawable.qr_code),
                            contentDescription = "QR Code",
                            modifier = Modifier.size(114.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                }
            })
    }
}