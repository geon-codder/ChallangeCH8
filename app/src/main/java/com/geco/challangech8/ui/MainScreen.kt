@file:Suppress("UnnecessaryVariable")

package com.geco.challangech8.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.asLiveData
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.geco.challangech8.AppDatastore
import com.geco.challangech8.MainActivity
import com.geco.challangech8.Routes

@Composable
fun ScreenMain(context: Context){
    (context as MainActivity)
    val lifecycleOwner = LocalLifecycleOwner.current
    val appDatastore = AppDatastore.getInstance(context)!!
    val navController = rememberNavController()
    var ruteAwal: String? = Routes.Login.route
    appDatastore.getLoginStatus.asLiveData().observe(lifecycleOwner) { preference ->
        val loginstatus = preference
        if(loginstatus == 0){
            ruteAwal = Routes.Login.route
        }else if(loginstatus == 1){
            ruteAwal = Routes.Dashboard.route
        }
    }
    NavHost(navController = navController, startDestination = ruteAwal.toString()) {

        composable(Routes.Login.route) {
            LoginPage(navController = navController, context)
        }

        composable(Routes.SignUp.route) {
            SignUp(navController = navController, context)
        }

        composable(Routes.MovieDetail.route) { navBackStack ->
            MovieDetail(navController = navController, context)
        }

        composable(Routes.Dashboard.route) {
            Dashboard(navController = navController, context)
        }
    }

}
