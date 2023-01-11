package com.github.carlosmateo89.ematemplate.config


import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.carlosmateo89.ematemplate.listeners.MyProjectManagerListener.Companion.projectInstance
import com.github.carlosmateo89.ematemplate.templates.*
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.psi.PsiManager

/**
 * Created by Carlos Mateo Benito on 2022
 *
 * <p>
 * Copyright (c) 2022 by AtSistemas. All rights reserved
 * </p>
 *
 * @author <a href="mailto: cmateo.benito@atsistemas.com">Carlos Mateo Benito</a>
 */
fun RecipeExecutor.emaRecipeFragmentSetup(
    moduleData: ModuleTemplateData,
    packageName: String,
    featureName: String,
    layoutBinding: String,
    addActivityContainer: Boolean,
    isToolbarActivity: Boolean,
    navigationGraph: String,
    hasNavigator: Boolean
) {
    val (projectData) = moduleData
    val project = projectInstance ?: return

    val virtualFiles = ProjectRootManager.getInstance(project).contentSourceRoots
    val virtSrc = virtualFiles.first { it.path.contains("src") }
    val virtRes = virtualFiles.first { it.path.contains("res") }
    val virtLayout = virtualFiles.first { it.path.contains("layout") }
    val virtNav = virtualFiles.first { it.path.contains("navigation") }
    val directorySrc = PsiManager.getInstance(project).findDirectory(virtSrc)!!
    val directoryRes = PsiManager.getInstance(project).findDirectory(virtRes)!!
    val directoryLayout = PsiManager.getInstance(project).findDirectory(virtLayout)!!
    val directoryNav = PsiManager.getInstance(project).findDirectory(virtNav)!!

    addViewState(packageName, featureName)
        .save(directorySrc, packageName, "${featureName}State.kt")

    addViewModel(packageName, featureName)
        .save(directorySrc, packageName, "${featureName}EmaViewModel.kt")

    addAndroidViewModel(packageName, featureName)
        .save(directorySrc, packageName, "${featureName}EmaAndroidViewModel.kt")

    addViewFragment(packageName, featureName, layoutBinding, hasNavigator)
        .save(directorySrc, packageName, "${featureName}Fragment.kt")

    xmlLayout.save(directoryLayout, packageName, "${layoutBinding}.xml")


    if (addActivityContainer) {
        xmlNavigation.save(directoryNav, packageName, "${navigationGraph}.xml")
        addViewActivity(
            packageName,
            featureName,
            layoutBinding,
            navigationGraph,
            false,
            isToolbarActivity,
            true
        )
            .save(directorySrc, packageName, "${featureName}Activity.kt")
    }

    if (hasNavigator) {
        addFragmentNavigator(packageName, featureName)
            .save(directorySrc, packageName, "${featureName}Navigator.kt")
        addDestination(packageName, featureName)
            .save(directorySrc, packageName, "${featureName}Destination.kt")
    }


}