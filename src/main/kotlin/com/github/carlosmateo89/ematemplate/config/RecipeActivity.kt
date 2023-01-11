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
fun RecipeExecutor.emaRecipeActivitySetup(
    moduleData: ModuleTemplateData,
    packageName: String,
    featureName: String,
    layoutBinding:String,
    navigationGraph:String,
    hasNavigator: Boolean,
    hasToolbar: Boolean
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

    addViewActivity(packageName, featureName,layoutBinding, navigationGraph,hasNavigator, hasToolbar,false)
        .save(directorySrc, packageName, "${featureName}Activity.kt")

    xmlLayout.save(directoryLayout, packageName, "${layoutBinding}.xml")
    xmlNavigation.save(directoryNav, packageName, "${navigationGraph}.xml")

    if (hasNavigator) {
        addActivityNavigator(packageName, featureName)
            .save(directorySrc, packageName, "${featureName}Navigator.kt")
        addDestination(packageName, featureName)
            .save(directorySrc, packageName, "${featureName}Destination.kt")
    }


}