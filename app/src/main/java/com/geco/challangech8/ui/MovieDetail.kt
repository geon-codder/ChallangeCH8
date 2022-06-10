package com.geco.challangech8.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.asLiveData
import androidx.navigation.NavHostController
import coil.compose.base.R
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.geco.challangech8.API_KEY.Companion.IMAGE_BASE
import com.geco.challangech8.AppDatastore
import com.geco.challangech8.component.CustomTopAppBar


@Composable
fun MovieDetail(navController: NavHostController,context: Context) {
    Box(modifier = Modifier.fillMaxSize()) {
        ScaffoldWithTopBarDetailMovie(navController,context)
    }
}

@Composable
fun ScaffoldWithTopBarDetailMovie(navController: NavHostController,context: Context) {
    Scaffold(
        topBar = {
            CustomTopAppBar(navController, "Movie Detail", true)
        }, content = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var judul = "judul"
                var poster = "poster"
                var overview = "overview"
                val lifecycleOwner = LocalLifecycleOwner.current
                val appDatastore = AppDatastore.getInstance(context)!!

                appDatastore.getMovieTitle.asLiveData().observe(lifecycleOwner) { preference ->
                    judul = preference
                }
                appDatastore.getMoviePoster.asLiveData().observe(lifecycleOwner) { preference ->
                    poster = preference
                }
                appDatastore.getMovieOverview.asLiveData().observe(lifecycleOwner) { preference ->
                    overview = preference
                }
                Text(
                    text = judul,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(2.dp))

                Image(
                    painter = rememberImagePainter(
                        data = IMAGE_BASE + poster,

                        builder = {
                            scale(Scale.FILL)
                            placeholder(R.drawable.notification_action_background)
                        }
                    ),
                    contentDescription = overview,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(5.0f)
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = overview,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(20.dp))

            }

        })
}
