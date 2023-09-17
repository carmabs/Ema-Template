package com.github.carlosmateo89.ematemplate.config


import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.carlosmateo89.ematemplate.templates.*
import org.jetbrains.kotlin.lombok.utils.capitalize

/**
 * Created by Carlos Mateo Benito on 2022
 *
 * <p>
 * Copyright (c) 2022 by AtSistemas. All rights reserved
 * </p>
 *
 * @author <a href="mailto: cmateo.benito@atsistemas.com">Carlos Mateo Benito</a>
 */
fun RecipeExecutor.emaRecipeUseCaseSetup(
    moduleData: ModuleTemplateData,
    featureName: String,
    output: String,
    hasInput:Boolean
) {
    addUseCase(
        packageName = moduleData.packageName,
        featureName = featureName.capitalize(),
        output = output.ifEmpty { "Unit" },
        hasInput = hasInput
    )
}