package com.qwert2603.multi_public.design.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qwert2603.multi_public.util.allCases
import com.qwert2603.multi_public.util.LoadingState

@Composable
fun FullscreenProgressIndicator(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun FullscreenLoadingError(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
    ) {
        Text("Loading error!")
        Spacer(Modifier.height(12.dp))
        Button(onRetry) {
            Text("Retry")
        }
    }
}

@Composable
fun <T> LoadingStateUi(
    loadingState: LoadingState<*, T>,
    onRetry: () -> Unit,
    successContent: @Composable (T) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        when (loadingState) {
            LoadingState.None -> Unit
            LoadingState.Loading -> FullscreenProgressIndicator()
            is LoadingState.Error -> FullscreenLoadingError(onRetry = onRetry)
            is LoadingState.Success -> successContent(loadingState.data)
        }.allCases
    }
}