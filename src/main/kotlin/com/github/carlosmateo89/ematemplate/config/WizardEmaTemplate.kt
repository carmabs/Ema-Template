package com.github.carlosmateo89.ematemplate.config

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider

/**
 * Created by Carlos Mateo Benito on 4/13/21
 *
 * <p>
 * Copyright (c) 2021 by AtSistemas. All rights reserved
 * </p>
 *
 * @author <a href="mailto: cmateo.benito@atsistemas.com">Carlos Mateo Benito</a>
 */
class WizardEmaTemplate : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> =
        listOf(emaSetupFragmentTemplate, emaSetupActivityTemplate)
}