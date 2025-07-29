package com.gopal.bazariko.ui.screens

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gopal.bazariko.R
import com.gopal.bazariko.ui.components.CustomDefaultBtn
import com.gopal.bazariko.ui.components.CustomTextField
import com.gopal.bazariko.ui.components.DefaultBackArrow
import com.gopal.bazariko.ui.components.ErrorSuggestion



@Composable
fun Login(navController: NavController) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var checkBox by remember {
        mutableStateOf(false)
    }
    val emailErrorState = remember {
        mutableStateOf(false)
    }
    val passwordErrorState = remember {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Box(modifier = Modifier.weight(0.7f)) {
                DefaultBackArrow {
                    navController.popBackStack()
                }
            }
            Box(modifier = Modifier.weight(1.0f)) {
                Text(text = "Sign in", color = Color.Gray, fontSize = 18.sp)
            }


        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Welcome Back", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text(
            text = "Sign in with your email or password\nor continue with social media.",
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(50.dp))
        CustomTextField(
            placeholder = "example@email.com",
            trailingIcon = R.drawable.mail,
            label = "Email",
            errorState = emailErrorState,
            keyboardType = KeyboardType.Email,
            visualTransformation = VisualTransformation.None,
            onChanged = { newEmail ->
                email = newEmail
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomTextField(
            placeholder = "********",
            trailingIcon = R.drawable.lock,
            label = "Password",
            keyboardType = KeyboardType.Password,
            errorState = passwordErrorState,
            visualTransformation = PasswordVisualTransformation(),
            onChanged = { newPass ->
                password = newPass
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        if (emailErrorState.value) {
            ErrorSuggestion("Please enter valid email address.")
        }
        if (passwordErrorState.value) {
            Row() {
                ErrorSuggestion("Please enter valid password.")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkBox, onCheckedChange = {
                        checkBox = it
                    },
                    colors = CheckboxDefaults.colors(checkedColor = Color.Gray)
                )
                Text(text = "Remember me", color = Color.Gray, fontSize = 14.sp)
            }
            Text(
                text = "Forget Password",
                color = Color.Gray,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                modifier = Modifier.clickable {
                    navController.navigate("forgot")
                }
            )
        }
        CustomDefaultBtn(shapeSize = 50f, btnText = "Continue") {
            val pattern = Patterns.EMAIL_ADDRESS
            val isEmailValid = pattern.matcher(email.text).matches()
            val isPassValid = password.text.length >= 8
            emailErrorState.value = !isEmailValid
            passwordErrorState.value = !isPassValid
            if (isEmailValid && isPassValid) {
                navController.navigate("home"){
                    popUpTo("login") {
                        inclusive = true
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 10.dp,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            Color(0xEBFFD6CA),
                            shape = CircleShape
                        )
                        .clickable {

                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google_icon),
                        contentDescription = "Google Login Icon"
                    )
                }
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            Color(0xEBFFD6CA),
                            shape = CircleShape
                        )
                        .clickable {

                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.x),
                        contentDescription = "Twitter Login Icon"
                    )
                }
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            Color(0xEBFFD6CA),
                            shape = CircleShape
                        )
                        .clickable {

                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.facebook_2),
                        contentDescription = "Facebook Login Icon"
                    )
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Don't have an account? ", color = Color.Gray)
                Text(
                    text = "Sign Up",
                    color = Color(0xFFEE5A2D),
                    modifier = Modifier.clickable {
                        navController.navigate("signup")
                    })
            }
        }


    }
}