package com.geco.challangech8.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.asLiveData
import androidx.navigation.NavHostController
import com.geco.challangech8.AppDatastore
import com.geco.challangech8.MovieViewModel
import com.geco.challangech8.Routes
import com.geco.challangech8.component.CustomTopAppBar
import com.geco.challangech8.model.Movie
import com.geco.challangech8.theme.Purple700


private val movieViewModel = MovieViewModel()

@Composable
fun Dashboard(navController: NavHostController,context: Context) {
    Box(modifier = Modifier.fillMaxSize()){
        DashboardPage(navController,context)
    }
}

@Composable
fun DashboardPage(navController: NavHostController,context: Context){
    Scaffold(
        topBar = {
            CustomTopAppBar(navController, "Home", false)
        }, content = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var username = "pengunjung"
                val lifecycleOwner = LocalLifecycleOwner.current
                val appDatastore = AppDatastore.getInstance(context)!!

                appDatastore.getUserName.asLiveData().observe(lifecycleOwner) { preference ->
                    username = preference
                }
                Row {
                    Text(
                        text = "Selamat Datang "+username,
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                    ClickableText(
                        text = AnnotatedString("Logout?"),
                        modifier = Modifier
                            .padding(5.dp),
                        onClick = { navController.navigate(Routes.Login.route) },
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Purple700
                        )
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                MovieList(movieList = movieViewModel.movieListResponse,navController,context)
                movieViewModel.getMovieData()
            }

        })
}

@Composable
fun MovieList(movieList: List<Movie>, navController: NavHostController,context: Context) {
    LazyColumn {
        itemsIndexed(items = movieList) { index, item ->
            MovieItem(movie = item,navController,context)
        }
    }
}