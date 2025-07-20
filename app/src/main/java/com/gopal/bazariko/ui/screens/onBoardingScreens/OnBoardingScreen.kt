package com.gopal.bazariko.ui.screens.onBoardingScreens

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gopal.bazariko.R
import com.gopal.bazariko.ui.components.CustomDefaultBtn

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {
    val splashImageList = listOf(
        R.drawable.splash_1,
        R.drawable.splash_2,
        R.drawable.splash_3,
    )
    val currentPosition = remember { mutableStateOf(0) }
    val animate = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        AnimatedContent(
            targetState = animate.value,
            modifier = Modifier
                .fillMaxWidth(),
            transitionSpec = {
                slideInHorizontally(
                    initialOffsetX = { value ->
                        value
                    }
                ) with slideOutHorizontally(
                    targetOffsetX = { value ->
                        -value
                    }
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
                        .padding(top = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Bazariko",
                        fontSize = 50.sp,
                        color = Color(0xFFEE5A2D),
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.muli_bold)),
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    when (currentPosition.value) {
                        0 -> {
                            Text(
                                text = buildAnnotatedString {
                                    append(text = "Welcome to ")
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            color = Color.DarkGray,
                                        )
                                    ) {
                                        append("Bazariko.")
                                    }
                                    append(" Let's Shop!")
                                },
                                color = Color.Gray,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily(Font(R.font.muli)),
                            )
                        }
                        1 -> {
                            Text(
                                text = "We help people connect with store\naround Bhilai",
                                color = Color.Gray,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                        else -> {
                            Text(
                                text = "We show easy way to shop.\nJust stay at home with us",
                                color = Color.Gray,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center
                            )

                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    Image(
                        painter = painterResource(id = splashImageList[currentPosition.value]),
                        contentDescription = "Splash Image",
                        modifier = Modifier.padding(40.dp)
                    )
                }
            }
        )


        DotIndicator(splashImageList.size, currentPosition.value)

        CustomDefaultBtn(btnText = "Continue", shapeSize = 10f) {
            if (currentPosition.value < 2) {
                currentPosition.value++
                animate.value = !animate.value
            } else {
                navController.navigate("login"){
                    popUpTo(0){ inclusive = true }
                }
            }
        }
    }
}