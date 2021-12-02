package com.qwert2603.multi_public.common.util

import android.app.Activity
import android.app.Application
import com.qwert2603.multi_public.util.ActivityLifecycleCallbacksAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StartedActivityProvider(private val application: Application) {

    private var _startedActivity = MutableStateFlow<Activity?>(null)

    val startedActivity: Flow<Activity?> = _startedActivity.asStateFlow()

    fun init() {
        application.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacksAdapter() {

            override fun onActivityStarted(activity: Activity) {
                _startedActivity.value = activity
            }

            override fun onActivityStopped(activity: Activity) {
                if (_startedActivity.value === activity) {
                    _startedActivity.value = null
                }
            }
        })
    }
}

