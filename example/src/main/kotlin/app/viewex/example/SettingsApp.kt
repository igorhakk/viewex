package app.viewex.example

import app.viewex.app.tmp.AbstractApp
import app.viewex.app.tmp.AppDetailsBuilder
import app.viewex.app.localization.TranslateService
import app.viewex.composer.layout.details.LayoutDetails
import app.viewex.core.details.DetailsDefinition

class SettingsApp(
    translateService: TranslateService
) : AbstractApp<User>() {

    override val detailsProvider: DetailsDefinition<LayoutDetails> =
        AppDetailsBuilder(name, translateService)
            .withIcon("settings")
            .build()
}
