package com.example.newsappcompose.screen.dropdown

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsappcompose.R
import com.example.newsappcompose.ui.theme.News
import com.example.newsappcompose.ui.theme.NewsAppComposeTheme

@Composable
fun DropdownScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(18.dp))
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .padding(start = 15.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(News)
                .width(63.dp)
                .height(30.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = null,
                Modifier
                    .padding(start = 12.dp, top = 5.dp)
                    .size(20.dp)
            )

        }
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = "EN",
            modifier = Modifier.padding(start = 28.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.W500
        )
        Spacer(modifier = Modifier.height(24.dp))
        Divider(modifier = Modifier.background(Color.Black))
        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "RU",
            modifier = Modifier.padding(start = 28.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.W500
        )
        Spacer(modifier = Modifier.height(23.dp))
        Divider(modifier = Modifier.background(Color.Black))

    }


}

@Preview(showBackground = true)
@Composable
fun DropdownScreenPreview() {
    NewsAppComposeTheme {
        DropdownScreen()
    }
}