package com.geco.challangech8

sealed class Routes(val route: String) {
    object SignUp : Routes("SignUp")
    object MovieDetail : Routes("MovieDetail")
    object Login : Routes("Login")
    object Dashboard : Routes("Dashboard")
}
