package app.viewex.ui

import app.viewex.app.AppLayout
import app.viewex.composer.ViewId

interface UiProvider {

    val activeSessions: Set<UiSession>

    val apps: Collection<AppLayout>

    fun getSession(rootId: ViewId): UiSession

    fun containsSession(rootId: ViewId): Boolean

    fun initUiView(): UiTemplate

    fun startSession(session: UiSession)

    fun closeSession(rootId: ViewId)

}
