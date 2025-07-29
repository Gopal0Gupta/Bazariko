package com.gopal.bazariko

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gopal.bazariko.ui.screens.Login
import com.gopal.bazariko.ui.screens.SplashScreen
import com.gopal.bazariko.ui.screens.onBoardingScreens.OnBoardingScreen
import com.gopal.bazariko.ui.theme.BazarikoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BazarikoTheme {
                val navController = rememberNavController()
                BazarikoNavHost(navController)
            }
        }
    }
}

@Composable
fun BazarikoNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("onBoard") {
            OnBoardingScreen(navController = navController)
        }
        composable("login") {
            Login(navController = navController)
        }
        composable("signup") {
            Signup(navController = navController)
        }
    }
}
