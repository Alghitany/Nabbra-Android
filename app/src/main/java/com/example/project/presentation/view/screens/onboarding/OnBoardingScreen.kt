package com.example.project.presentation.view.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.project.presentation.view.reusable.CustomButton
import com.example.project.presentation.view.screens.loginOrRegister.LoginOrRegisterScreen
import com.example.project.ui.theme.MidNightBlue
import kotlinx.coroutines.launch

class OnBoardingScreen : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val pagerState = rememberPagerState(pageCount = { 2 })
        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MidNightBlue),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Page Content
            Box {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.wrapContentSize()
                ) { page ->
                    when (page) {
                        0 -> PageContent1()
                        1 -> PageContent2()
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            //Page Dots Indicator
            Box(
                modifier = Modifier.padding(top = 25.dp)
            ) {
                PageIndicator(
                    numberOfPages = 2,
                    selectedPage = pagerState.currentPage,
                    defaultRadius = 10.dp,
                    selectedLength = 10.dp,
                    space = 10.dp
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            //Continue Button
            CustomButton(
                text = if (pagerState.currentPage == 0) "Continue" else "Start Now",
                onClick = {
                    coroutineScope.launch {
                        if (pagerState.currentPage < 1) { // Check if it's not the last page
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } else {
                            navigator.push(LoginOrRegisterScreen())
                        }
                    }
                }
            )
        }
    }
}
