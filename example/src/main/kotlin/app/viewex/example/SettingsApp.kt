package app.viewex.example

import app.viewex.app.App
import app.viewex.app.AppDetailsBuilder
import app.viewex.app.localization.TranslateService
import app.viewex.composer.layout.details.LayoutDetails
import app.viewex.core.details.DetailsProvider

class SettingsApp(
    translateService: TranslateService
) : App<User>() {

    override val detailsProvider: DetailsProvider<LayoutDetails> =
        AppDetailsBuilder(name, translateService)
            .withIcon("settings")
            .build()
}
