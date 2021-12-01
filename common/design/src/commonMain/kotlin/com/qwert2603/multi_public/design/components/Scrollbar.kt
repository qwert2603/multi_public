package com.qwert2603.multi_public.design.components

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

expect val verticalScrollbarWidth: Dp

@Composable
expect fun VerticalScrollbar(
    lazyListState: LazyListState,
    modifier: Modifier = Modifier,
)