package com.github.carlosmateo89.ematemplate.config

import com.android.tools.idea.wizard.template.Category
import com.android.tools.idea.wizard.template.CheckBoxWidget
import com.android.tools.idea.wizard.template.Constraint
import com.android.tools.idea.wizard.template.FormFactor
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.TemplateData
import com.android.tools.idea.wizard.template.TextFieldWidget
import com.android.tools.idea.wizard.template.WizardUiContext
import com.android.tools.idea.wizard.template.booleanParameter
import com.android.tools.idea.wizard.template.stringParameter
import com.android.tools.idea.wizard.template.template

/**
 * Created by Carlos Mateo Benito on 2022
 *
 * <p>
 * Copyright (c) 2022 by AtSistemas. All rights reserved
 * </p>
 *
 * @author <a href="mailto: cmateo.benito@atsistemas.com">Carlos Mateo Benito</a>
 */

val emaSetupActivityTemplate
    get() = template {
        name = "EMA activity architecture"
        description =
            "Creates a new EMA activity feature with the following classes - ViewModel, Activity, Navigator and State"
        minApi = 21
        category = Category.Other// Check other categories
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
            WizardUiContext.NewProject, WizardUiContext.NewModule
        )

        val featureName = stringParameter {
            name = "Feature Name"
            constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
            default = ""
            help = "The name of the feature that requires EMA views"
        }

        val layoutBinding = stringParameter {
            name = "Layout binding for activity"
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { "${featureName.value}_activity" }
            help = "The binding layout generated class to represent the activity view"
        }

        val navigator = booleanParameter {
            name = "Create Navigator"
            default = true
            help = "Set true to use a defined ema navigator. False if screen has no navigation"
        }

        val hasToolbar = booleanParameter {
            name = "Create the activity with toolbar support"
            default = false
            help = "Set true if the activity has toolbar support. False if is a normal activity"
        }

        val navigationActivityGraph = stringParameter {
            name = "Navigation graph for the activity"
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { "${featureName.value}_graph" }
            help = "The activity navigation graph to define the destinations"
        }

        widgets(
            TextFieldWidget(featureName),
            CheckBoxWidget(navigator),
            CheckBoxWidget(hasToolbar),
            TextFieldWidget(navigationActivityGraph),
        )

        recipe = { data: TemplateData ->
            emaRecipeActivitySetup(
                moduleData = data as ModuleTemplateData,
                packageName = data.packageName,
                featureName = featureName.value,
                layoutBinding = layoutBinding.value,
                navigationGraph = navigationActivityGraph.value,
                hasNavigator = navigator.value,
                hasToolbar = hasToolbar.value
            )
        }
    }

