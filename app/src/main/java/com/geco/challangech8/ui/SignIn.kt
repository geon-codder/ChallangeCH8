package com.geco.challangech8.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.asLiveData
import androidx.navigation.NavHostController
import com.geco.challangech8.AppDatastore
import com.geco.challangech8.MainActivity
import com.geco.challangech8.Routes
import com.geco.challangech8.theme.Purple700

@Composable
fun LoginPage(navController: NavHostController, context: Context) {
    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString("Sign up here"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            onClick = { navController.navigate(Routes.SignUp.route) },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple700
            )
        )
    }
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        (context as MainActivity)
        val usernameState = remember { mutableStateOf(TextFieldValue()) }
        val passwordState = remember { mutableStateOf(TextFieldValue()) }
        val lifecycleOwner = LocalLifecycleOwner.current
        val appDatastore = AppDatastore.getInstance(context)!!

        Text(text = "Login", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Username") },
            value = usernameState.value,
            onValueChange = { usernameState.value = it })
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password") },
            value = passwordState.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { passwordState.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
//                onClick = { navController.navigate(Routes.Dashboard.route) },
                onClick = {
                    if (usernameState.value.toString().isEmpty() or passwordState.value.toString().isEmpty()){
                        Toast.makeText(context,"Username atau Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                    } else {
                        appDatastore.getUserName.asLiveData().observe(lifecycleOwner) { name ->
                            appDatastore.getUserPassword.asLiveData()
                                .observe(lifecycleOwner) { password ->
                                    if ((usernameState.value.toString() == name) and (passwordState.value.toString() == password)) {
                                        // Login Berhasil
                                        Toast.makeText(context, "Berhasil", Toast.LENGTH_SHORT)
                                            .show()
                                        navController.navigate(Routes.Dashboard.route)
                                    } else Toast.makeText(
                                        context,
                                        "Username or Password Wrong",
                                        Toast.LENGTH_SHORT
                                    ).show()  // Login Gagal
                                }
                        }
                    }
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Login")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString("Forgot password?"),
            onClick = { navController.navigate(Routes.ForgotPassword.route) },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default
            )
        )
    }
}
