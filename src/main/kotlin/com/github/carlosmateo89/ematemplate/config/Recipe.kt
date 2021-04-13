package com.github.carlosmateo89.ematemplate.config


import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.github.carlosmateo89.ematemplate.listeners.MyProjectManagerListener.Companion.projectInstance
import com.github.carlosmateo89.ematemplate.templates.*
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.PsiManager
import org.jetbrains.kotlin.idea.KotlinLanguage

/**
 * Created by Carlos Mateo Benito on 4/13/21
 *
 * <p>
 * Copyright (c) 2021 by AtSistemas. All rights reserved
 * </p>
 *
 * @author <a href="mailto: cmateo.benito@atsistemas.com">Carlos Mateo Benito</a>
 */
fun RecipeExecutor.emaRecipeSetup(
        moduleData: ModuleTemplateData,
        packageName: String,
        featureName: String,
        addActivity: Boolean,
        navigatorName: String?
) {
    val (projectData) = moduleData
    val project = projectInstance ?: return

    addAllKotlinDependencies(moduleData)

    val virtualFiles = ProjectRootManager.getInstance(project).contentSourceRoots
    val virtSrc = virtualFiles.first { it.path.contains("src") }
    val virtRes = virtualFiles.first { it.path.contains("res") }
    val directorySrc = PsiManager.getInstance(project).findDirectory(virtSrc)!!
    val directoryRes = PsiManager.getInstance(project).findDirectory(virtRes)!!

    addViewState(packageName, featureName)
            .save(directorySrc, packageName, "${featureName}State.kt")

    addViewModel(packageName, featureName, navigatorName)
            .save(directorySrc, packageName, "${featureName}ViewModel.kt")

    addAndroidViewModel(packageName, featureName)
            .save(directorySrc, packageName, "${featureName}AndroidViewModel.kt")


    addViewFragment(packageName, featureName, addActivity, navigatorName)
            .save(directorySrc, packageName, "${featureName}Fragment.kt")

    if (addActivity) {
        addViewActivity(packageName, featureName, navigatorName)
                .save(directorySrc, packageName, "${featureName}Activity.kt")
    }

    if (navigatorName.isNullOrEmpty()) {
        addNavigator(packageName, featureName)
                .save(directorySrc, packageName, "${featureName}Navigator.kt")
        addAndroidNavigator(packageName, featureName)
                .save(directorySrc, packageName, "${featureName}AndroidNavigator.kt")
    }


}

fun String.save(srcDir: PsiDirectory, subDirPath: String, fileName: String) {
    try {
        val destDir = subDirPath.split(".").toDir(srcDir)
        val psiFile = PsiFileFactory
                .getInstance(srcDir.project)
                .createFileFromText(fileName, KotlinLanguage.INSTANCE, this)
        destDir.add(psiFile)
    } catch (exc: Exception) {
        exc.printStackTrace()
    }
}

fun List<String>.toDir(srcDir: PsiDirectory): PsiDirectory {
    var result = srcDir
    forEach {
        result = result.findSubdirectory(it) ?: result.createSubdirectory(it)
    }
    return result
}