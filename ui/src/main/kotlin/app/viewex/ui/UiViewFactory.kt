package app.viewex.ui

import app.viewex.core.secutity.Principal

fun interface UiViewFactory<PrincipalType : Principal<*, *>> {
    fun create(uiContext: UiContext<PrincipalType>): UiView
}
