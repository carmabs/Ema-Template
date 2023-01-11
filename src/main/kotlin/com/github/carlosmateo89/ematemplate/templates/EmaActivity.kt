package com.github.carlosmateo89.ematemplate.templates

import android.databinding.tool.ext.toCamelCase
import org.jetbrains.kotlin.idea.gradleTooling.capitalize

fun addViewActivity(
    packageName: String,
    featureName: String,
    layoutBinding: String,
    navigationGraph: String,
    hasNavigator: Boolean,
    hasToolbar: Boolean,
    isContainer: Boolean
): String {

    val layoutBindingName = "${layoutBinding.toCamelCase().capitalize()}Binding"
    val navigationGraphName = "R.navigation.${navigationGraph}"
    val containerImports = if (isContainer) {
        "import com.carmabs.ema.android.viewmodel.EmaAndroidEmptyViewModel\n" +
                "import com.carmabs.ema.core.state.EmaEmptyState\n" +
                "import com.carmabs.ema.core.viewmodel.EmaEmptyViewModel"
    } else
        "import com.carmabs.ema.android.di.injectDirect"

    val viewmodel = if (isContainer) {
        "EmaEmptyViewModel"
    } else
        "${featureName}ViewModel"

    val androidViewModel = if (isContainer) {
        "EmaAndroidEmptyViewModel"
    } else
        "injectDirect()"

    val state = if (isContainer) {
        "EmaEmptyState"
    } else
        "${featureName}State"

    val navigator =
        if (hasNavigator) "override val navigator: EmaNavigator<${featureName}Destination> = ${featureName}Navigator(" +
                "this," +
                "R.id.navHostFragment," +
                navigationGraphName +
                ")" else "override val navigator: EmaNavigator<EmaEmptyDestination> = EmaActivityNavControllerHost(" +
                "this," +
                "R.id.navHostFragment," +
                navigationGraphName +
                ")"
    val navigatorName = if (hasNavigator) "${featureName}Destination" else "EmaEmptyDestination"
    val activityImports = (if (hasToolbar) "import androidx.appcompat.widget.Toolbar\n" +
            "import com.carmabs.ema.android.ui.EmaToolbarActivity\n" +
            "import com.google.android.material.appbar.AppBarLayout"
    else
        "import com.carmabs.ema.android.ui.EmaActivity") +
            if (isContainer) {
                "\nimport com.carmabs.ema.core.state.EmaEmptyState\n" +
                        "import com.carmabs.ema.core.viewmodel.EmaEmptyViewModel"
            } else ""


    val activityToolbar = if (hasToolbar) "EmaToolbarActivity" else "EmaActivity"
    val toolbar =
        if (hasToolbar) "\n   override fun ${layoutBindingName}.provideToolbar(): Toolbar {\n" +
                "      \n" +
                "    }\n" +
                "\n" +
                "   override fun ${layoutBindingName}.provideToolbarLayout(): AppBarLayout {\n" +
                "        \n\n" +
                "    }" else ""
    val navigatorImport =
        if (hasNavigator) "" else "import com.carmabs.ema.android.navigation.EmaActivityNavControllerHost\n" +
                "import com.carmabs.ema.core.navigator.EmaEmptyDestination"
    return """package $packageName

import android.view.LayoutInflater
$activityImports
$containerImports
import com.carmabs.ema.android.viewmodel.EmaAndroidViewModel
import com.carmabs.ema.core.navigator.EmaNavigator
$navigatorImport

class ${featureName}Activity :
    ${activityToolbar}<${layoutBindingName},${state}, ${viewmodel}, $navigatorName>() {

    override fun createViewBinding(inflater: LayoutInflater): ${layoutBindingName} {
        return ${layoutBindingName}.inflate(inflater)
    }
    $toolbar
    override fun provideAndroidViewModel(): EmaAndroidViewModel {
        return $androidViewModel
    }

    override fun ${layoutBindingName}.onNormal(data: ${state}){
    
    }

    ${navigator}
}
"""
}