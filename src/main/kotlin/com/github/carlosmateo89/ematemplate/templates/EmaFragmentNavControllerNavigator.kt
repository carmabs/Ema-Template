package com.github.carlosmateo89.ematemplate.templates

fun addFragmentNavigator(packageName: String,
                 featureName: String
) = """package $packageName

import androidx.fragment.app.Fragment
import com.bimbaylola.architecture_android.navigation.ArcFragmentNavControllerNavigator

class ${featureName}Navigator(
    fragment: Fragment
) : ArcFragmentNavControllerNavigator<${featureName}Destination>(fragment) {

    override fun navigate(navigationTarget: ${featureName}Destination) {
        
    }
}
"""