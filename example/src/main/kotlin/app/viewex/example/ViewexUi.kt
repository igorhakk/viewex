package app.viewex.example

import app.viewex.app.localization.TranslateService
import app.viewex.composer.layout.details.LayoutDetails
import app.viewex.core.details.DetailsProvider
import app.viewex.ui.DefaultUiContext
import app.viewex.ui.UiContext
import app.viewex.ui.UiDetailsBuilder
import app.viewex.ui.Ui

class ViewexUi(
    private val translateService: TranslateService,
    settingsApp: SettingsApp
) : Ui<User>() {

    private val settingsApp = registerApp(settingsApp)

    override val detailsProvider: DetailsProvider<LayoutDetails> =
        UiDetailsBuilder(this.name, translateService)
            .withIcon("apps")
            .build()

    override fun createContext(): UiContext<User> = DefaultUiContext()

}
