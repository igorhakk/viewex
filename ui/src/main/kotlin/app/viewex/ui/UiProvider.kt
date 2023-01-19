package app.viewex.ui

import app.viewex.app.AppDefinition
import app.viewex.composer.ViewId
import app.viewex.composer.layout.details.LayoutDetails
import app.viewex.composer.layout.details.NamedLayout
import app.viewex.core.details.DetailsDefinition
import app.viewex.core.secutity.Principal

interface UiProvider<PrincipalType : Principal<*, *>> : DetailsDefinition<LayoutDetails>, NamedLayout {

    val activeSessions: Set<UiSession<PrincipalType>>

    val apps: Collection<AppDefinition<PrincipalType>>

    fun getSession(rootId: ViewId): UiSession<PrincipalType>

    fun containsSession(rootId: ViewId): Boolean

    fun initUiView(): UiView

    fun startSession(session: UiSession<PrincipalType>)

    fun closeSession(rootId: ViewId)

}
