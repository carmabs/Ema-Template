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
import com.android.tools.idea.wizard.template.impl.defaultPackageNameParameter
import com.android.tools.idea.wizard.template.stringParameter
import com.android.tools.idea.wizard.template.template
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

val emaSetupActivityTemplate
    get() = template {
        name = "ARC activity architecture"
        description =
            "Creates a new ARC activity feature with the following classes - ViewModel, Activity, Navigator and State"
        minApi = 23
        category = Category.Other// Check other categories
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry,
            WizardUiContext.NewProject, WizardUiContext.NewModule
        )

        val featureName = stringParameter {
            name = "Feature Name"
            constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
            default = ""
            help = "The name of the feature that requires ARC views"
        }

        val layoutBinding = stringParameter {
            name = "Layout binding for activity"
            default = ""
            constraints = listOf(Constraint.NONEMPTY, Constraint.CLASS)
            suggest = { "${featureName.value.lowercase()}_activity" }
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
            constraints = listOf(Constraint.NONEMPTY, Constraint.NAVIGATION)
            default = ""
            suggest = { "${featureName.value.lowercase()}_graph" }
            help = "The activity navigation graph to define the destinations"
        }

        widgets(
            TextFieldWidget(featureName),
            TextFieldWidget(layoutBinding),
            CheckBoxWidget(hasToolbar),
            CheckBoxWidget(navigator),
            TextFieldWidget(navigationActivityGraph)
        )

        recipe = { data: TemplateData ->
            emaRecipeActivitySetup(
                moduleData = data as ModuleTemplateData,
                featureName = featureName.value.capitalize(),
                layoutBinding = layoutBinding.value,
                navigationGraph = navigationActivityGraph.value,
                hasNavigator = navigator.value,
                hasToolbar = hasToolbar.value
            )
        }
    }

