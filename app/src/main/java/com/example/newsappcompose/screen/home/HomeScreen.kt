package com.example.newsappcompose.screen.home

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.newsappcompose.ui.theme.Date
import com.example.newsappcompose.ui.theme.News
import com.example.newsappcompose.ui.theme.NewsAppComposeTheme
import com.example.newsappcompose.ui.theme.Search
import com.example.newsappcompose.ui.theme.SearchLabel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val search = remember {
        mutableStateOf("")
    }

    val uiState = viewModel.homeUistate.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = search.value) {
        viewModel.getNews(search.value)
    }

    Scaffold { it ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            item {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(27.dp, top = 33.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ellipse),
                            contentDescription = null,
                            modifier = Modifier
                                .size(18.dp)

                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "News Catcher", fontWeight = FontWeight.W700,
                            fontSize = 20.sp
                        )

                    }
                    Spacer(modifier = Modifier.height(31.dp))
                    Text(
                        text = "March 26th, 2022", modifier = Modifier.padding(start = 27.dp),
                        fontSize = 13.sp, fontWeight = FontWeight.W500, color = Date
                    )
                    Spacer(modifier = Modifier.height(41.dp))


                }


            }
            item {
                LazyRow {
                    items(uiState.value.topNews) {
                        HomeCardRow(it)
                    }
                }
            }
            item {
                OutlinedTextField(
                    value = search.value,
                    onValueChange = { search.value = it },
                    Modifier
                        .padding(start = 27.dp, top = 25.dp, end = 57.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Search,
                        unfocusedBorderColor = Search
                    ), label = {
                        Text(
                            text = "Search ...", fontSize = 11.sp,
                            fontWeight = FontWeight.W500, color = SearchLabel
                        )
                    }

                )
                Spacer(modifier = Modifier.height(13.dp))
            }
            item {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Box(
                        modifier = Modifier
                            .padding(end = 31.dp, bottom = 10.dp)
                            .height(26.dp)
                            .width(75.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(News)
                    ) {
                        Text(text = "EN", Modifier.padding(start = 12.dp))
                        Image(
                            painter = painterResource(id = R.drawable.foto),
                            contentDescription = null,
                            Modifier
                                .padding(end = 11.dp, bottom = 5.dp, top = 5.dp)
                                .size(16.dp)
                                .align(Alignment.BottomEnd)
                        )


                    }
                }
            }
            items(uiState.value.newsList) {
                HomeCardColumn(it) { model ->
                    navController.navigate(Screen.Detail.createRoute(model))
                }
            }
        }
    }
}
