package com.qwert2603.multi_public.design.components

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

actual val verticalScrollbarWidth = 0.dp

@Composable
actual fun VerticalScrollbar(
    lazyListState: LazyListState,
    modifier: Modifier,
) = Unit