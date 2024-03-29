package com.github.carlosmateo89.ematemplate.config

import com.android.tools.idea.wizard.template.*
import org.jetbrains.kotlin.lombok.utils.capitalize

/**
 * Created by Carlos Mateo Benito on 2022
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved
 * </p>
 *
 * @author <a href="mailto: cmateo.benito@atsistemas.com">Carlos Mateo Benito</a>
 */

val emaSetupComposableTemplate
    get() = template {
        name = "EMA composable architecture"
        description = "Creates a new EMA composable feature with the following classes - ViewModel, ComposableScreen, ComposableScreenActions, Navigator and State"
        minApi = 23
        category = Category.Other // Check other categories
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
            WizardUiContext.NewProject, WizardUiContext.NewModule
        )

        val featureName = stringParameter {
            name = "Feature Name"
            default = ""
            constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
            help = "The name of the feature that requires EMA views"
        }

        val hasActions = booleanParameter {
            name = "Create Composable Screen Actions"
            default = true
            help = "Set true to bind composable actions for screen. False if screen has no composable actions"
        }

        val hasNavigator = booleanParameter {
            name = "Create Navigator"
            default = true
            help = "Set true to use a defined ema navigator. False if screen has no navigation"
        }


        widgets(
            TextFieldWidget(featureName),
            CheckBoxWidget(hasActions),
            CheckBoxWidget(hasNavigator),
            )

        recipe = { data: TemplateData ->
            emaRecipeComposableSetup(
                moduleData = data as ModuleTemplateData,
                featureName = featureName.value.capitalize(),
                hasNavigator = hasNavigator.value,
                hasActions = hasActions.value
            )
        }
    }
