package com.appvillis.nicegram

import android.content.Context
import com.appvillis.assistant_core.view.NgWidgetsEntryPoint
import com.appvillis.core_domain.usecase.call
import dagger.hilt.EntryPoints
import kotlinx.coroutines.launch
import timber.log.Timber

object NgWidgetsTrackerHelper {

    private fun entryPoint(context: Context) = EntryPoints
        .get(context.applicationContext, NgWidgetsEntryPoint::class.java)

    fun viewTrackVisibilityHelper(isVisible: Boolean, context: Context) {
        val entryPoints = entryPoint(context)

        entryPoints.appScope().launch {
            if (isVisible) {
                Timber.d("onBannerVisible")
                entryPoints.trackPinnedBannerShownUseCase().call()
            } else {
                entryPoints.trackPinnedBannerHiddenUseCase().call()
                Timber.d("onExitFromScreen")
            }
        }
    }
}
