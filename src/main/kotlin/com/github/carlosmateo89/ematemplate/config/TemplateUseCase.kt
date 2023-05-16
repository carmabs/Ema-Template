package com.github.carlosmateo89.ematemplate.config

import com.android.tools.idea.wizard.template.*
import com.github.carlosmateo89.ematemplate.templates.addUseCase
import org.jetbrains.kotlin.lombok.utils.capitalize

/**
 * Created by Carlos Mateo Benito on 2023
 *
 * <p>
 * Copyright (c) 2023 by Carmabs. All rights reserved
 * </p>
 *
 * @author <a href="mailto: cmateo.benito@atsistemas.com">Carlos Mateo Benito</a>
 */

val emaSetupUseCaseTemplate
    get() = template {
        name = "ARC UseCase"
        description = "Creates a new use case"
        minApi = 23
        category = Category.Other // Check other categories
        formFactor = FormFactor.Generic
        screens = listOf(
            WizardUiContext.NewProjectExtraDetail,
            WizardUiContext.MenuEntry
        )

        val featureName = stringParameter {
            name = "Name for the use case"
            default = ""
            constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
            help = "The name of the use case"
        }

        val outputClass = stringParameter {
            name = "The class name for the output. Set it empty for Unit output"
            default = ""
            constraints = listOf(Constraint.CLASS)
            help = "The class name for the output. Set it empty for Unit output"
        }

        val hasInput = booleanParameter {
            name = "Has input?.Set true if you want to generate an input with the use case"
            default = true
        }

        widgets(
            TextFieldWidget(featureName),
            TextFieldWidget(outputClass),
            CheckBoxWidget(hasInput)
        )

        recipe = { data: TemplateData ->
            emaRecipeUseCaseSetup(
                moduleData = (data as ModuleTemplateData),
                featureName = featureName.value.capitalize(),
                output = outputClass.value.ifEmpty { "Unit" },
                hasInput = hasInput.value
            )
        }
    }
