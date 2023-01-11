package com.github.carlosmateo89.ematemplate.templates

fun addFragmentNavigator(packageName: String,
                 featureName: String
) = """package $packageName

import androidx.fragment.app.Fragment
import com.carmabs.ema.android.navigation.EmaFragmentNavControllerNavigator

class ${featureName}Navigator(
    fragment: Fragment
) : EmaFragmentNavControllerNavigator<${featureName}Destination>(fragment) {

    override fun navigate(navigationTarget: ${featureName}Destination) {
        
    }
}
"""