package com.geco.challangech8.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import com.geco.challangech8.AppDatastore
import com.geco.challangech8.MainActivity
import com.geco.challangech8.Routes
import com.geco.challangech8.component.CustomTopAppBar
import kotlinx.coroutines.launch

@Composable
fun SignUp(navController: NavHostController,context: Context) {
    Box(modifier = Modifier.fillMaxSize()) {
        ScaffoldWithTopBar(navController,context)
    }
}

@Composable
fun ScaffoldWithTopBar(navController: NavHostController,context: Context) {
    Scaffold(
        topBar = {
            CustomTopAppBar(navController, "Signup", true)
        }, content = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                (context as MainActivity)
                val username = remember { mutableStateOf(TextFieldValue()) }
                val password = remember { mutableStateOf(TextFieldValue()) }
                val repassword = remember { mutableStateOf(TextFieldValue()) }
                val lifecycleOwner = LocalLifecycleOwner.current
                val appDatastore = AppDatastore.getInstance(context)!!

                Text(text = "SignUp", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))
                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    label = { Text(text = "Username") },
                    value = username.value,
                    onValueChange = { username.value = it })
                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    label = { Text(text = "Password") },
                    value = password.value,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = { password.value = it })
                Spacer(modifier = Modifier.height(20.dp))


                TextField(
                    label = { Text(text = "Repassword") },
                    value = repassword.value,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = { repassword.value = it })
                Spacer(modifier = Modifier.height(20.dp))

                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
//                        onClick = { navController.navigate(Routes.Login.route) },
                        onClick = {
                            if (username.value.toString().isEmpty() or password.value.toString().isEmpty()){
                                Toast.makeText(context,"Username atau Password tidak boleh kosong",Toast.LENGTH_SHORT).show()
                            } else {
                                // Buat Akun Baru (Update Data di Datastore)
                                lifecycleOwner.lifecycleScope.launch {
                                    appDatastore.setUserName(username.value.toString())
                                    appDatastore.setUserPassword(password.value.toString())
                                }.invokeOnCompletion {
                                    navController.navigate(Routes.Login.route)
                                }
                            }
                        },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Register")
                    }
                }

            }

        })
}