package com.example.newsappcompose.screen.detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.newsappcompose.Article
import com.example.newsappcompose.R
import com.example.newsappcompose.ui.theme.Jennifer
import com.example.newsappcompose.ui.theme.News
import com.example.newsappcompose.ui.theme.NewsAppComposeTheme
import com.example.newsappcompose.ui.theme.White

@Composable
fun DetailScreen(
    article: Article,
    viewModel: DetailViewModel = hiltViewModel()
) {

    val uiState = viewModel.detailUiState.collectAsStateWithLifecycle()

    val drop = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        viewModel.getDetail(article)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f)
                .background(Color.Red)
        ) {
            AsyncImage(
                model = uiState.value.article?.urlToImage,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    Modifier
                        .padding(start = 15.dp, top = 20.dp)
                        .width(119.dp)
                        .height(30.dp)
                        .clip(
                            RoundedCornerShape(32.dp)
                        )
                        .background(
                            News
                        ),
                    contentAlignment = Alignment.TopStart,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = null,
                        Modifier
                            .padding(top = 5.dp, start = 10.dp)
                            .size(20.dp)
                    )
                    Text(
                        text = uiState.value.article?.author.orEmpty(),
                        fontSize = 10.sp,
                        modifier = Modifier.padding(start = 35.dp, top = 7.dp, end = 8.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.W600,
                        color = Color.Black
                    )
                }
                Column {
                    Image(painter = painterResource(id = R.drawable.group),
                        contentDescription = null,
                        Modifier
                            .padding(end = 22.dp, top = 17.dp)
                            .size(35.dp)
                            .clickable { drop.value = true })

                    DropdownMenu(
                        expanded = drop.value, onDismissRequest = { drop.value = false },
                        properties = PopupProperties(clippingEnabled = true)
                    ) {
                        Row {
                            Image(
                                painter = painterResource(id = if (uiState.value.article?.isSaved == true) R.drawable.bookmark else R.drawable.save),
                                contentDescription = null,
                                Modifier
                                    .padding(start = 21.dp, top = 5.dp, bottom = 12.dp)
                                    .size(25.dp)
                                    .clickable {
                                        viewModel.addSaved(article)
                                    }
                            )
                            Text(
                                text = "Save",
                                modifier = Modifier.padding(start = 35.dp, top = 5.dp),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W500
                            )
                        }

                        Divider(color = Color.Black)

                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "Share", modifier = Modifier.padding(start = 27.dp),
                                    fontSize = 16.sp, fontWeight = FontWeight.W500
                                )
                            },
                            onClick = { /*TODO*/ },
                            leadingIcon = {
                                Image(
                                    painter = painterResource(id = R.drawable.share),
                                    contentDescription = null,
                                    Modifier
                                        .padding(start = 6.dp, top = 5.dp)
                                        .size(25.dp)
                                )


                            })
                    }

                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxHeight(1f)
                .background(White)
                .clip(RoundedCornerShape(topEnd = 32.dp, topStart = 32.dp))
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .padding(top = 28.dp, start = 33.dp)
                        .background(News)
                        .width(86.dp)
                        .height(22.dp)
                        .clip(RoundedCornerShape(size = 32.dp))
                ) {
                    Text(
                        text = uiState.value.article?.source?.name.orEmpty(),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.W500,
                        modifier = Modifier.padding(
                            start = 22.dp,
                            end = 22.dp,
                            top = 5.dp,
                            bottom = 4.dp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = uiState.value.article?.title.orEmpty(),
                    modifier = Modifier.padding(start = 37.dp, end = 34.dp),
                    fontSize = 26.sp, fontWeight = FontWeight.W700
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row(Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = uiState.value.article?.author.orEmpty(),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W700,
                            color = Jennifer,
                            modifier = Modifier
                                .align(
                                    Alignment.TopEnd
                                )
                                .padding(end = 34.dp)
                        )

                    }
                }
                Spacer(modifier = Modifier.height(11.dp))
                Text(
                    text = uiState.value.article?.content.orEmpty(),
                    Modifier.padding(start = 37.dp, end = 34.dp),
                    fontWeight = FontWeight.W700,
                    fontSize = 15.sp, maxLines = 5, overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = uiState.value.article?.description.orEmpty(),
                    Modifier.padding(start = 33.dp, end = 34.dp),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.W500, maxLines = 9, overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(11.dp))
                Row(Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Read more...",
                            fontWeight = FontWeight.W600,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .align(
                                    Alignment.TopEnd
                                )
                                .padding(end = 33.dp),
                            color = Jennifer
                        )
                    }
                }


            }


        }


    }

}




