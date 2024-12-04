package com.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.navigator.Navigator
import com.example.project.presentation.view.screens.chooseEarphone.ChooseEarphone
import com.example.project.presentation.view.screens.splash.SplashScreen
import com.example.project.presentation.viewmodel.LoginViewModel
import com.example.project.ui.theme.ProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectTheme {
                val viewModel : LoginViewModel = viewModel ()
                Navigator (screen = ChooseEarphone(viewModel.getName(),viewModel.getEmail()))
            }
        }
    }
}