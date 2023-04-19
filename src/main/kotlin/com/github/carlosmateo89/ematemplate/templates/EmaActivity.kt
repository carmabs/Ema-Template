package com.github.carlosmateo89.ematemplate.templates

import android.databinding.tool.ext.toCamelCase

fun addViewActivity(
    packageName: String,
    modulePackageName: String?,
    featureName: String,
    layoutBinding: String,
    navigationGraph: String,
    hasNavigator: Boolean,
    hasToolbar: Boolean,
    isContainer: Boolean
): String {
    val layoutBindingName =
        "${layoutBinding.toCamelCase().replaceFirstChar { it.uppercaseChar() }}Binding"
    val rImports = modulePackageName?.let {
        var import = "import $it.databinding.$layoutBindingName"
        if (isContainer) {
            import += "\nimport $modulePackageName.R"
        }
    } ?: ""

    val navigationGraphName = "R.navigation.${navigationGraph}"
    val containerImports = if (isContainer) {
        "import com.bimbaylola.architecture_android.viewmodel.ArcAndroidEmptyViewModel\n" +
                "import com.bimbaylola.architecture_core.state.ArcEmptyState\n" +
                "import com.bimbaylola.architecture_core.viewmodel.ArcEmptyViewModel"
    } else
        "import com.bimbaylola.architecture_android.di.injectDirect"

    val viewmodel = if (isContainer) {
        "ArcEmptyViewModel"
    } else
        "${featureName}ViewModel"

    val androidViewModel = if (isContainer) {
        "ArcAndroidEmptyViewModel()"
    } else
        "${featureName}AndroidViewModel(injectDirect())"

    val state = if (isContainer) {
        "ArcEmptyState"
    } else
        "${featureName}State"

    val navigator =
        if (hasNavigator) "override val navigator: ArcNavigator<${featureName}Destination> = ${featureName}Navigator(\n" +
                "       this,\n" +
                "       R.id.navHostFragment,\n" +
                "       $navigationGraphName\n" +
                "   )" else "override val navigator: ArcNavigator<ArcEmptyDestination> = ArcActivityNavControllerHost(\n" +
                "       this,\n" +
                "       R.id.navHostFragment,\n" +
                "       $navigationGraphName\n" +
                "   )"
    val navigatorName = if (hasNavigator) "${featureName}Destination" else "ArcEmptyDestination"
    val activityImports = (if (hasToolbar) "import androidx.appcompat.widget.Toolbar\n" +
            "import com.bimbaylola.architecture_android.ui.ArcToolbarActivity\n" +
            "import com.google.android.material.appbar.AppBarLayout"
    else
        "import com.bimbaylola.architecture_android.ui.ArcActivity")


    val activityToolbar = if (hasToolbar) "ArcToolbarActivity" else "ArcActivity"
    val toolbar =
        if (hasToolbar) "\n    override fun ${layoutBindingName}.provideToolbar(): Toolbar {\n" +
                "       return tb$featureName\n" +
                "    }\n" +
                "\n" +
                "    override fun ${layoutBindingName}.provideToolbarLayout(): AppBarLayout {\n" +
                "       return abl$featureName\n" +
                "    }\n" else ""
    val navigatorImport =
        if (hasNavigator) "" else "import com.bimbaylola.architecture_android.navigation.ArcActivityNavControllerHost\n" +
                "import com.bimbaylola.architecture_core.navigator.ArcEmptyDestination"
    return """package $packageName

import android.view.LayoutInflater
$activityImports
$containerImports
import com.bimbaylola.architecture_android.viewmodel.ArcAndroidViewModel
import com.bimbaylola.architecture_core.navigator.ArcNavigator
$navigatorImport
$rImports

class ${featureName}Activity :
    ${activityToolbar}<${layoutBindingName},${state}, ${viewmodel}, $navigatorName>() {

    override fun createViewBinding(inflater: LayoutInflater): ${layoutBindingName} {
        return ${layoutBindingName}.inflate(inflater)
    }
    $toolbar
    override fun provideAndroidViewModel(): ArcAndroidViewModel {
        return $androidViewModel
    }

    override fun ${layoutBindingName}.onStateNormal(data: ${state}){
    
    }

    ${navigator}
}
"""
}