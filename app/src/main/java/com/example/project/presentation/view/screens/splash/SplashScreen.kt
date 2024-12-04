package com.example.project.presentation.view.screens.splash

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.min
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.project.R
import com.example.project.presentation.view.screens.homeScreen.HomeScreen
import com.example.project.presentation.view.screens.onboarding.OnBoardingScreen
import com.example.project.ui.theme.MidNightBlue
import kotlinx.coroutines.delay

class SplashScreen(private val name: String?,private val email: String?) : Screen {
    @Composable
    override fun Content() {

        val context = LocalContext.current
        val navigator = LocalNavigator.currentOrThrow


        val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

        //Logo
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MidNightBlue),
            contentAlignment = Alignment.Center
        ) {
            BoxWithConstraints(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                val imageSize = min(maxWidth, maxHeight) * 0.6f

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Splash Logo",
                    modifier = Modifier.size(imageSize),
                    contentScale = ContentScale.Fit
                )
            }
        }

        // Navigate to OnboardingScreen
        LaunchedEffect(Unit) {
            delay(3000)

            // Retrieve the token from SharedPreferences
            val token = sharedPreferences.getString("auth_token", null)

            if (token != null) {
                // If token exists, go to the home screen
                navigator.replace(HomeScreen(name = name, email = email))
            } else {
                // If no token, go to the onboarding screen
                navigator.replace(OnBoardingScreen())
            }
        }
    }
}
