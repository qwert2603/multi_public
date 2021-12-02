package com.qwert2603.multi_public.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class AppCoroutineScope : CoroutineScope by CoroutineScope(Dispatchers.Main + SupervisorJob())