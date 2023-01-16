package app.viewex.ui

import app.viewex.composer.Lifecycle
import app.viewex.composer.View
import app.viewex.composer.ViewContext

interface UiContext : ViewContext, Lifecycle {

    override val session: UiSession

    val uiView: UiView

    fun initSession(session: UiSession)

    fun setUiDefaultContent(view: View)

    fun renderUiContent(view: View)

}
