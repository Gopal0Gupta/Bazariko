package com.gopal.bazariko.ui.screens

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gopal.bazariko.R
import com.gopal.bazariko.datastore.DataStoreManager
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    val dataStore = remember { DataStoreManager(context) }
    val isFirstTime by dataStore.isFirstTime.collectAsState(initial = true)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bazariko_logo),
            contentDescription = "Bazariko Logo",
            modifier = Modifier.size(200.dp).clip(CircleShape)
        )
    }

    LaunchedEffect(isFirstTime) {
        delay(2000L)
        if (!isFirstTime) {
            navController.navigate("login"){
                popUpTo(0){ inclusive=true }
            }
        } else {
            navController.navigate("onBoard"){
                popUpTo(0){ inclusive=true }
            }
            dataStore.saveIsFirstTime(false)
        }
    }

}
