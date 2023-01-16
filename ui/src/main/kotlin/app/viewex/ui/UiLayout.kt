package app.viewex.ui

import app.viewex.app.AppLayout
import app.viewex.composer.ViewId

abstract class UiLayout : UiProvider {

    private val uiContextMap: MutableMap<ViewId, UiContext> = mutableMapOf()

    private val sessionSet = SessionSet()

    final override val activeSessions: Set<UiSession> get() = sessionSet.allSessions

    final override val apps: Collection<AppLayout>
        get() = TODO("Not yet implemented")

    final override fun getSession(rootId: ViewId): UiSession = sessionSet[rootId]

    final override fun containsSession(rootId: ViewId): Boolean = sessionSet.contains(rootId)

    final override fun initUiView(): UiTemplate = createContext().also {
        uiContextMap[it.session.rootId] = it
    }.uiView.template

    final override fun startSession(session: UiSession) = uiContextMap[session.rootId]?.initSession(session)
            ?: throw IllegalStateException("Session [ ${session.rootId} ] not initialized")

    final override fun closeSession(rootId: ViewId) {
        sessionSet.remove(rootId)
        uiContextMap.remove(rootId)
    }

    protected abstract fun createContext(): UiContext

}
