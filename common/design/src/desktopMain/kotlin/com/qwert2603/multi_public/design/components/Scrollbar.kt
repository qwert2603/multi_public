package com.qwert2603.multi_public.design.components

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

actual val verticalScrollbarWidth = 8.dp

@Composable
actual fun VerticalScrollbar(
    lazyListState: LazyListState,
    modifier: Modifier,
) {
    androidx.compose.foundation.VerticalScrollbar(
        adapter = rememberScrollbarAdapter(lazyListState),
        modifier = modifier,
    )
}