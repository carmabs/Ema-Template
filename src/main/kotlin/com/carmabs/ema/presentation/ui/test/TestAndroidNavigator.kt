package com.carmabs.ema.presentation.ui.test

import android.app.Activity
import androidx.navigation.NavController
import com.carmabs.ema.android.navigation.EmaAndroidNavigator

class TestAndroidNavigator(
    override val navController: NavController,
    override val activity:Activity
) : EmaAndroidNavigator<TestNavigator.Navigation>,TestNavigator {

    override fun toDestination() {

    }
}
