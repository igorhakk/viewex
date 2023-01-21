package app.viewex.ui

import app.viewex.app.tmp.AppLayout
import app.viewex.composer.Route
import app.viewex.composer.View
import app.viewex.composer.ViewId
import app.viewex.composer.layout.details.DetailedLayout
import app.viewex.core.secutity.Principal
import app.viewex.composer.layout.details.LayoutType as AbstractLayoutType

interface UiProvider<PrincipalType : Principal<*, *>> : DetailedLayout<PrincipalType> {

    val activeSessions: Set<UiSession<PrincipalType>>

    val apps: Collection<AppLayout<PrincipalType>>

    fun getContext(rootId: ViewId): UiContext<PrincipalType>

    fun initializedContext(rootId: ViewId): Boolean

    fun initUiLayout(route: Route): View

    fun startSession(session: UiSession<PrincipalType>)

    fun closeSession(rootId: ViewId)

    object LayoutType : AbstractLayoutType("ui")
}
