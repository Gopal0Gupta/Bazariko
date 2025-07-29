package com.gopal.bazariko

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gopal.bazariko.ui.screens.Login
import com.gopal.bazariko.ui.screens.Signup
import com.gopal.bazariko.ui.screens.SplashScreen
import com.gopal.bazariko.ui.screens.forgot
import com.gopal.bazariko.ui.screens.home
import com.gopal.bazariko.ui.screens.onBoardingScreens.OnBoardingScreen
import com.gopal.bazariko.ui.screens.otp
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
        composable("otp") {
            otp(navController = navController)
        }
        composable("forgot") {
            forgot(navController = navController)
        }
        composable("home") {
            home(navController = navController)
        }
    }
}
