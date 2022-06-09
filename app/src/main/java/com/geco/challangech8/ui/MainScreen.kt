package com.geco.challangech8.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.geco.challangech8.MainActivity
import com.geco.challangech8.Routes

@Composable
fun ScreenMain(context: Context){
    (context as MainActivity)
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(Routes.Login.route) {
            LoginPage(navController = navController, context)
        }

        composable(Routes.SignUp.route) {
            SignUp(navController = navController, context)
        }

        composable(Routes.ForgotPassword.route) { navBackStack ->
            ForgotPassword(navController = navController)
        }

        composable(Routes.Dashboard.route) {
            Dashboard(navController = navController)
        }
    }
}
