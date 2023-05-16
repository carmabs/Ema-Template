package com.github.carlosmateo89.ematemplate.templates

fun addAndroidViewModel(
        packageName: String,
        featureName: String,
        hasNavigationEvents:Boolean
) : String {
        val imports = if(hasNavigationEvents){
             ""
        }
        else{
                "import com.bimbaylola.architecture_core.navigator.ArcEmptyNavigationEvent"
        }
        val navigationEvent = if(hasNavigationEvents){
                "${featureName}NavigationEvent"
        }else{
                "ArcEmptyNavigationEvent"
        }
        return """package $packageName

import com.bimbaylola.architecture_android.viewmodel.ArcAndroidViewModel
$imports

class ${featureName}AndroidViewModel(viewModel: ${featureName}ViewModel) :
    ArcAndroidViewModel<${featureName}State,$navigationEvent>(viewModel)

"""
}