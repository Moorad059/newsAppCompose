package com.example.newsappcompose.screen.saved

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.newsappcompose.R
import com.example.newsappcompose.navigation.Screen
import com.example.newsappcompose.screen.home.HomeCardColumn
import com.example.newsappcompose.ui.theme.Date
import com.example.newsappcompose.ui.theme.NewsAppComposeTheme

@Composable
fun SavedScreen(
    navController: NavController,
    viewModel: SavedViewModel = hiltViewModel()
) {

    val uiState = viewModel.savedUistate.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getSaved()
    }

    Column(Modifier.fillMaxSize()) {
        Row(Modifier.padding(start = 33.dp, top = 27.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ellipse), contentDescription = null,
                Modifier
                    .padding(top = 6.dp)
                    .size(18.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "News Catcher", fontSize = 20.sp, fontWeight = FontWeight.W700)

        }
        Spacer(modifier = Modifier.height(34.dp))
        Text(
            text = "March 26th, 2022",
            fontWeight = FontWeight.W500,
            fontSize = 13.sp,
            modifier = Modifier.padding(start = 33.dp), color = Date
        )
        Spacer(modifier = Modifier.height(31.dp))
        LazyColumn {
            items(uiState.value.newsList) {
                HomeCardColumn(it) {
                    navController.navigate(Screen.Detail.createRoute(it))
                }
            }

        }
    }


}
