package com.example.newsappcompose.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.newsappcompose.Article
import com.example.newsappcompose.ui.theme.Date
import com.example.newsappcompose.ui.theme.News
import com.example.newsappcompose.ui.theme.White

@Composable
fun HomeCardColumn(model: Article, onClick: (Article) -> Unit) {

    Card(
        modifier = Modifier
            .background(Color.White)
            .padding(start = 27.dp, end = 20.dp, bottom = 33.dp)
            .clickable {
                onClick.invoke(model)
            }
    ) {
        Row(Modifier.background(White)) {
            Column(modifier = Modifier.weight(0.7f)) {
                Box(
                    modifier = Modifier
                        .background(News)
                        .clip(RoundedCornerShape(32.dp))
                        .background(
                            White
                        ), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Finance", fontSize = 10.sp, fontWeight = FontWeight.W500,
                        modifier = Modifier.padding(
                            start = 19.dp,
                            end = 19.dp,
                            top = 3.dp,
                            bottom = 2.dp
                        )
                    )
                }

                Text(
                    text = model.title,
                    fontWeight = FontWeight.W700, maxLines = 2, overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp, modifier = Modifier.padding(start = 2.dp)

                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 3.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = model.publishedAt,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.W500,
                        color = Date
                    )
                    Text(
                        text = "Jennifer Wars",
                        modifier = Modifier.padding(end = 10.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W700,
                        color = Color.Black
                    )
                }

            }
            AsyncImage(
                model = model.urlToImage,
                contentDescription = "",
                Modifier
                    .padding(start = 3.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .height(80.dp)
                    .weight(0.3f), contentScale = ContentScale.FillBounds
            )

        }


    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview1() {

}
