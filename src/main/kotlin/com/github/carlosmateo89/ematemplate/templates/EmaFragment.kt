package com.github.carlosmateo89.ematemplate.templates

import android.databinding.tool.ext.toCamelCase

fun addViewFragment(
    packageName: String,
    modulePackageName:String?,
    featureName: String,
    layoutBinding:String,
    hasNavigator: Boolean
): String {
    val layoutBindingName = "${layoutBinding.toCamelCase().replaceFirstChar { it.uppercaseChar() }}Binding"
    val rImports = modulePackageName?.let {
         "import $it.databinding.$layoutBindingName"
    }?:""
    val navigator =
        if (hasNavigator) "override val navigator: ArcNavigator<${featureName}Destination> = ${featureName}Navigator(" +
                "this" +
                ")" else "override val navigator: ArcNavigator<ArcEmptyDestination>? = null"
    val navigatorName = if (hasNavigator) "${featureName}Destination" else "ArcEmptyDestination"
    val navigatorImport =
        if (hasNavigator) "" else "import com.bimbaylola.architecture_core.navigator.ArcEmptyDestination"
    return """package $packageName

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bimbaylola.architecture_android.di.injectDirect
import com.bimbaylola.architecture_android.ui.ArcFragment
import com.bimbaylola.architecture_android.viewmodel.ArcAndroidViewModel
import com.bimbaylola.architecture_core.navigator.ArcNavigator
$navigatorImport
$rImports

class ${featureName}Fragment :
    ArcFragment<${layoutBindingName},${featureName}State, ${featureName}ViewModel, $navigatorName>() {

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): $layoutBindingName {
        return ${layoutBindingName}.inflate(inflater,container,false)
    }

    override fun provideAndroidViewModel(): ArcAndroidViewModel {
        return ${featureName}AndroidViewModel(injectDirect())
    }

    override fun ${layoutBindingName}.onStateNormal(data: ${featureName}State){
    
    }

    ${navigator}
}
"""
}