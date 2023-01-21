package app.viewex.example

import app.viewex.app.localization.TranslateService
import app.viewex.ui.AbstractUi
import app.viewex.ui.UiContext
import app.viewex.ui.UiLayout
import app.viewex.ui.UiSession
import app.viewex.ui.internal.DefaultUiContext
import app.viewex.ui.internal.DefaultUiLayout

class ViewexUi(
    private val translateService: TranslateService,
    settingsApp: SettingsApp
) : AbstractUi<User>(translateService) {
    override fun createContext(
        session: UiSession<User>
    ): UiContext<User> = DefaultUiContext(session)

    override fun createLayout(): UiLayout<User> = DefaultUiLayout()
}
