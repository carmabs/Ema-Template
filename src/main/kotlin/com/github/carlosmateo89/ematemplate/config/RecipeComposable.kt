package com.github.carlosmateo89.ematemplate.config


import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.carlosmateo89.ematemplate.listeners.MyProjectManagerListener.Companion.projectInstance
import com.github.carlosmateo89.ematemplate.templates.*
import com.intellij.psi.PsiManager
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
fun RecipeExecutor.emaRecipeComposableSetup(
    moduleData: ModuleTemplateData,
    featureName: String,
    hasNavigationEvents: Boolean,
    hasNavigator: Boolean,
    hasActions: Boolean
) {
    val (projectData) = moduleData
    val project = projectInstance ?: return
    val module = project.allModules().find { it.name == moduleData.name }!!

    val packageName = moduleData.packageName


    val virtualFiles = module.sourceRoots
    val virtJavaKotlin = virtualFiles.firstOrNull { it.path.contains("src/main/java") }
        ?: virtualFiles.first { it.path.contains("src/main/kotlin") }
    val directorySrc = PsiManager.getInstance(project).findDirectory(virtJavaKotlin)!!


    addViewState(packageName, featureName)
        .save(directorySrc, packageName, "${featureName}State.kt")

    if (hasActions) {
        addActions(
            packageName,
            featureName
        )
            .save(directorySrc, packageName, "${featureName}Action.kt")

        addViewModelAction(packageName, featureName, hasNavigationEvents)
            .save(directorySrc, packageName, "${featureName}ViewModel.kt")

    } else {
        addViewModel(packageName, featureName, hasNavigationEvents)
            .save(directorySrc, packageName, "${featureName}ViewModel.kt")

    }

    addAndroidViewModel(packageName, featureName, hasNavigationEvents)
        .save(directorySrc, packageName, "${featureName}AndroidViewModel.kt")

    addComposableScreenContent(packageName, featureName, hasActions)
        .save(directorySrc, packageName, "${featureName}ScreenContent.kt")


    if (hasNavigator) {
        addComposableNavigator(packageName, featureName)
            .save(directorySrc, packageName, "${featureName}Navigator.kt")
    }
    if (hasNavigationEvents) {
        addDestination(packageName, featureName)
            .save(directorySrc, packageName, "${featureName}NavigationEvent.kt")
    }
}