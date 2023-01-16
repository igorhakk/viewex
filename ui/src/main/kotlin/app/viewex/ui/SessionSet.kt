package app.viewex.ui

import app.viewex.composer.ViewId

class SessionSet {

    private val sessionMap: MutableMap<ViewId, UiSession> = mutableMapOf()

    val allSessions: Set<UiSession> get() = sessionMap.values.toSet()

    operator fun get(
        rootId: ViewId
    ) : UiSession = getOrNull(rootId) ?: throw IllegalStateException("Session [ $rootId ] not found")

    fun getOrNull(rootId: ViewId): UiSession? = sessionMap[rootId]

    fun add(session: UiSession) {
        if (contains(session.rootId))
            throw IllegalStateException("Session [ ${session.rootId} ] already exists")
        sessionMap[session.rootId] = session
    }

    fun remove(rootId: ViewId) {
        if (!contains(rootId))
            throw IllegalStateException("Session [ $rootId ] not found")
        sessionMap.remove(rootId)
    }

    fun contains(rootId: ViewId): Boolean = sessionMap.containsKey(rootId)

}
