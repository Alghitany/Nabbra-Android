package com.example.project.presentation.view.screens.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.project.R
import com.example.project.data.model.LoginRequest
import com.example.project.presentation.view.reusable.CustomButton
import com.example.project.presentation.view.reusable.CustomIcon
import com.example.project.presentation.view.reusable.CustomOutlinedTextField
import com.example.project.presentation.view.reusable.isValidEmail
import com.example.project.presentation.view.reusable.isValidPassword
import com.example.project.presentation.view.screens.chooseEarphone.ChooseEarphone
import com.example.project.presentation.view.screens.register.RegisterScreen
import com.example.project.presentation.viewmodel.LoginViewModel
import com.example.project.ui.theme.LightGreen
import com.example.project.ui.theme.LightGreenWithOpacity18
import com.example.project.ui.theme.MidNightBlue
import com.example.project.ui.theme.WhiteOpacity20
import com.example.project.ui.theme.WhiteOpacity80
import com.example.project.ui.theme.centuryGothic
import com.example.project.ui.theme.inder
import com.example.project.ui.theme.mulish

class LoginScreen:Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: LoginViewModel = hiltViewModel()
        val context = LocalContext.current

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        var isPasswordVisible by remember { mutableStateOf(false) }

        var errorMessage by remember { mutableStateOf("") }

        // Validate inputs
        fun validateInputs(): Boolean {
            return when {
                email.isEmpty() -> {
                    errorMessage = "Email cannot be empty"
                    false
                }
                !isValidEmail(email) -> {
                    errorMessage = "Invalid email format"
                    false
                }
                password.isEmpty() -> {
                    errorMessage = "Password cannot be empty"
                    false
                }
                !isValidPassword(password) -> {
                    errorMessage = "Password must be at least 8 characters long and include uppercase, lowercase, number, and special character"
                    false
                }
                else -> {
                    errorMessage = ""
                    true
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MidNightBlue)
                .padding(top = 55.dp, start = 17.dp, end = 17.dp)
        ) {
            //BackIcon
            CustomIcon(
                onClick = {navigator.pop()}
            )

            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "Welcome Back!",
                style = TextStyle(
                    fontFamily = inder,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.W400,
                    lineHeight = 45.sp,
                    color = Color.White,
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "We are happy to see you again!:)",
                style = TextStyle(
                    fontFamily = inder,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400,
                    lineHeight = 22.7.sp,
                    color = WhiteOpacity80,
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(60.dp))

            // Email TextField
            CustomOutlinedTextField(
                value = email,
                onValueChange = {email = it},
                placeholderText = "Email",
                leadingIcon = R.drawable.email
            )

            Spacer(modifier = Modifier.height(21.dp))

            // Password TextField
            CustomOutlinedTextField(
                placeholderText = "Password",
                value = password,
                onValueChange = {password = it},
                leadingIcon = R.drawable.lock,
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            painter = painterResource(
                                id = if (isPasswordVisible) R.drawable.eye_open else R.drawable.eye_closed
                            ),
                            contentDescription = "Password visibility toggle",
                            tint = WhiteOpacity20
                        )
                    }
                },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            )

            Spacer(modifier = Modifier.height(30.dp))

            val annotatedText1 = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = LightGreen,
                        fontWeight = FontWeight.W700,
                        fontSize = 14.sp,
                        fontFamily = centuryGothic,
                    )
                ) {
                    append("Forgot the Password ")
                }
                withStyle(
                    style = SpanStyle(
                        color = LightGreen,
                        fontSize = 14.sp,
                        fontFamily = inder,
                    )
                ){
                    append("?") // Append the "?" outside the clickable annotation.
                }
            }

            ClickableText(
                text = annotatedText1,
                style = TextStyle(
                    color = LightGreen,
                    fontFamily = centuryGothic,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W700,
                    lineHeight = 15.5.sp,
                ),
                onClick = {
                    /*TODO*/
                    /*navigator.push(ForgotPasswordScreen1())*/
                },
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(45.dp))

            CustomButton(
                text = "Login" ,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                contentPadding = ButtonDefaults. ContentPadding,
                onClick = {
                    if (validateInputs()) {
                        viewModel.login(
                            loginRequest = LoginRequest(email=email,
                                password = password),
                            onSuccessfulLogin = {navigator.push(ChooseEarphone(name=viewModel.getName(),email=viewModel.getEmail()))},
                            onFailedLogin = {
                                Toast.makeText(context,"Invalid Email or Password",Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                },
            )

            // Error Message
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    style = TextStyle(
                        color = LightGreen,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            Spacer(modifier = Modifier.height(45.dp))

            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .background(LightGreenWithOpacity18)
                )
                Text(
                    text = "or",
                    style = TextStyle(
                        color = LightGreen,
                        fontFamily = mulish,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W700,
                        lineHeight = 17.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(horizontal = 15.dp)
                )
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .background(LightGreenWithOpacity18)
                )
            }

            Spacer(modifier = Modifier.height(35.dp))

            Row(
                modifier = Modifier
                    .width(220.dp)
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                LoginIconButton(image = R.drawable.google)

                LoginIconButton(image = R.drawable.facebook)

                LoginIconButton(image = R.drawable.apple)

            }

            Spacer(modifier = Modifier.height(30.dp))

            val annotatedText = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.White,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        fontFamily = inder,
                    )
                ) {
                    append("Already have an account? ")
                }
                pushStringAnnotation(tag = "login", annotation = "login_action")
                withStyle(
                    style = SpanStyle(
                        color = LightGreen,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        fontFamily = inder,
                    )
                ) {
                    append("Sign Up")
                }
                pop()
            }

            ClickableText(
                text = annotatedText,
                onClick = { offset ->
                    annotatedText.getStringAnnotations(offset, offset).firstOrNull()?.let { annotation ->
                        if (annotation.tag == "login") {
                            navigator.push(RegisterScreen())
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}