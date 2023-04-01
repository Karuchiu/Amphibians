package com.example.amphibians.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.model.AmphibianArticle
import com.example.amphibians.ui.theme.AmphibiansTheme

@Composable
fun HomeScreen(
    amUiState: AmUiState,
    modifier: Modifier = Modifier
) {
    when(amUiState){
        is AmUiState.Loading -> LoadingScreen()
        is AmUiState.Success -> ArticlesScreen(amUiState.articles)
        is AmUiState.Error -> ErrorScreen()
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(id = R.drawable.loading_img),
            contentDescription = null
        )
    }
}

@Composable
fun ArticlesScreen(
    amArticles: List<AmphibianArticle>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(items = amArticles, key = {it.name}){
            ArticleCard(amArticle = it)
        }
    }
}

@Composable
fun ArticleCard(
    amArticle: AmphibianArticle,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current as? Context
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .aspectRatio(1f),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${amArticle.name} (${amArticle.type})",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .padding(top = 16.dp)
            )
            Text(
                text = amArticle.description,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
            )

            AsyncImage(
                model = ImageRequest.Builder(context = context!!)
                    .data(amArticle.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img)
            )

        }
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(stringResource(id = R.string.loading_failed))
    }
}


@Preview(showBackground = true)
@Composable
fun ArticleCardPreview() {
    AmphibiansTheme() {
        val mockCard = AmphibianArticle("Cow", "Mammal","I give milk","https://fakeurl.com/image.jpg")
        ArticleCard(amArticle =  mockCard)
    }
}
