package com.github.carlosmateo89.ematemplate.templates

fun addAndroidNavigator(packageName: String,
                        featureName: String
) =
        """package $packageName

import android.app.Activity
import androidx.navigation.NavController
import com.carmabs.ema.android.navigation.EmaAndroidNavigator

class ${featureName}AndroidNavigator(
    override val navController: NavController,
    override val activity:Activity
) : EmaAndroidNavigator<${featureName}Navigator.Navigation>,${featureName}Navigator {

    override fun toDestination() {

    }
}
"""