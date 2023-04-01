package com.example.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.model.AmphibianArticle

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
        modifier = modifier.fillMaxSize()
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
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        elevation = 8.dp
    ) {
        Row {
            Text(text = "${amArticle.name} (${amArticle.type})")
            Text(text = amArticle.description)
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amArticle.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
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
