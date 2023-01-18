package app.viewex.ui

import app.viewex.composer.ViewContext
import app.viewex.core.secutity.Principal

interface UiContext<PrincipalType : Principal<*, *>> : ViewContext {

    override val session: UiSession<PrincipalType>

    val uiView: UiView

    fun initSession(session: UiSession<PrincipalType>)


}
