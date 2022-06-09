package com.geco.challangech8.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.geco.challangech8.MovieItem
import com.geco.challangech8.MovieViewModel
import com.geco.challangech8.component.CustomTopAppBar
import com.geco.challangech8.model.Movie


private val movieViewModel = MovieViewModel()

@Composable
fun Dashboard(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()){
        DashboardPage(navController)
    }
}

@Composable
fun DashboardPage(navController: NavHostController){
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
                Row {
                    Text(
                        text = "Selamat Datang",
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                MovieList(movieList = movieViewModel.movieListResponse)
                movieViewModel.getMovieList()
            }

        })
}

@Composable
fun MovieList(movieList: List<Movie>) {
    LazyColumn {
        itemsIndexed(items = movieList) { index, item ->
            MovieItem(movie = item)
        }
    }
}