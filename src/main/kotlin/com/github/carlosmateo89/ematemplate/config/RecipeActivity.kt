package com.github.carlosmateo89.ematemplate.config


import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.carlosmateo89.ematemplate.listeners.MyProjectManagerListener.Companion.projectInstance
import com.github.carlosmateo89.ematemplate.templates.addActivityLayout
import com.github.carlosmateo89.ematemplate.templates.addActivityNavigator
import com.github.carlosmateo89.ematemplate.templates.addAndroidViewModel
import com.github.carlosmateo89.ematemplate.templates.addNavigationEvent
import com.github.carlosmateo89.ematemplate.templates.addViewActivity
import com.github.carlosmateo89.ematemplate.templates.addViewModel
import com.github.carlosmateo89.ematemplate.templates.addViewState
import com.github.carlosmateo89.ematemplate.templates.addXmlNavigation
import com.intellij.psi.PsiManager
import org.jetbrains.android.dom.manifest.getPrimaryManifestXml
import org.jetbrains.android.facet.AndroidFacet
import org.jetbrains.kotlin.idea.util.projectStructure.allModules
import org.jetbrains.kotlin.idea.util.sourceRoots

/**
 * Created by Carlos Mateo Benito on 2022
 *
 * <p>
 * Copyright (c) 2022 by AtSistemas. All rights reserved
 * </p>
 *
 * @author <a href="mailto: cmateo.benito@atsistemas.com">Carlos Mateo Benito</a>
 */
fun RecipeExecutor.emaRecipeActivitySetup(
    moduleData: ModuleTemplateData,
    featureName: String,
    layoutBinding: String,
    navigationGraph: String,
    hasNavigator: Boolean,
    hasToolbar: Boolean
) {
    val (projectData) = moduleData
    val project = projectInstance ?: return
    val module = project.allModules().find { it.name == moduleData.name }!!

    val androidFacet = AndroidFacet.getInstance(module)!!
    val packageName = moduleData.packageName
    val modulePackageName = androidFacet.getPrimaryManifestXml()?.packageName


    val virtualFiles = module.sourceRoots
    val virtJavaKotlin = virtualFiles.firstOrNull { it.path.contains("src/main/java") }
        ?: virtualFiles.first { it.path.contains("src/main/kotlin") }
    val virtRes = virtualFiles.first { it.path.contains("src/main/res") }
    val directorySrc = PsiManager.getInstance(project).findDirectory(virtJavaKotlin)!!
    val directoryRes = PsiManager.getInstance(project).findDirectory(virtRes)!!

    addActivityLayout(featureName, hasToolbar).save(directoryRes, "layout", "${layoutBinding}.xml")
    addXmlNavigation(featureName, moduleData.packageName, false).save(
        directoryRes,
        "navigation",
        "${navigationGraph}.xml"
    )


    addViewState(packageName, featureName)
        .save(directorySrc, packageName, "${featureName}State.kt")

    addViewModel(packageName, featureName, hasNavigator)
        .save(directorySrc, packageName, "${featureName}ViewModel.kt")

    addAndroidViewModel(packageName, featureName, hasNavigator)
        .save(directorySrc, packageName, "${featureName}AndroidViewModel.kt")

    addViewActivity(
        packageName,
        modulePackageName,
        featureName,
        layoutBinding,
        navigationGraph,
        hasNavigator,
        hasToolbar,
        false
    )
        .save(directorySrc, packageName, "${featureName}Activity.kt")


    if (hasNavigator) {
        addActivityNavigator(packageName, featureName)
            .save(directorySrc, packageName, "${featureName}Navigator.kt")
        addNavigationEvent(packageName, featureName)
            .save(directorySrc, packageName, "${featureName}NavigationEvent.kt")
    }


}