package com.example.newsappcompose.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.newsappcompose.Article
import com.example.newsappcompose.R
import com.example.newsappcompose.ui.theme.News
import com.example.newsappcompose.ui.theme.NewsAppComposeTheme

@Composable
fun HomeCardRow(item:Article){
    Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.padding(end = 15.dp, start = 17.dp)
        .width(320.dp)
        .height(180.dp)) {
        Box{
            AsyncImage(model =item.urlToImage,
                contentDescription = null,Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds)
            Box(contentAlignment = Alignment.Center, modifier = Modifier
                .padding(start = 18.dp, top = 18.dp)
                .background(
                    News
                )){

                Text(text = "News", fontSize = 11.sp,
                    modifier = Modifier.padding(start = 28.dp, top = 5.dp,
                        end = 28.dp, bottom = 4.dp),
                    fontWeight = FontWeight.W500, color = Color.Black)
            }
            Text(text =item.title,maxLines = 2, overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(
                        Alignment.BottomCenter
                    )
                    .padding(start = 18.dp, bottom = 3.dp), color = Color.Black)

        }

    }


}
@Preview(showBackground = true)
@Composable
fun HomeCardPreview(){
    NewsAppComposeTheme {

    }
}
