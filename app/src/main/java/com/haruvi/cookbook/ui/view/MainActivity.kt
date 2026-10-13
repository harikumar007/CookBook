package com.haruvi.cookbook.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.haruvi.cookbook.domain.entity.Categories
import com.haruvi.cookbook.ui.theme.CookBookTheme
import com.haruvi.cookbook.ui.viewmodel.CategoryViewModel
import com.haruvi.cookbook.utility.UiData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CookBookTheme {
                Scaffold(topBar = {
                    TopAppBar(title = { Text("Cook Book") })
                }, content = {padding->
                    CategoriesScreen(padding)
                })

            }
        }
    }
}

@Composable
fun CategoriesScreen(paddingValues: PaddingValues = PaddingValues(0.dp), viewModel: CategoryViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center){
        when(val current = state){
            is UiData.Loading -> CircularProgressIndicator()
            is UiData.Success -> {
                LazyColumn {
                    items(current.response) { item ->
                        CategoryItem(item)
                    }
                }
            }
            is UiData.Error ->{
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = current.error, color = MaterialTheme.colorScheme.error)
                    Button(onClick = {
                        viewModel.loadData()
                    }) {
                        Text(text = "Retry")
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryItem(item: Categories) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp).height(100.dp)) {
        Row(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = item.strCategoryThumb,
                contentDescription = item.idCategory,
                modifier = Modifier.size(100.dp)
            )
            Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                Text(text = item.strCategory.toString(), fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(text = item.strCategoryDescription.toString(), fontSize = 12.sp, modifier = Modifier.verticalScroll(
                    rememberScrollState()
                ))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CookBookTheme {
        CategoriesScreen()
    }
}