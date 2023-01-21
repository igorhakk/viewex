package app.viewex.ui

import app.viewex.composer.ViewId
import app.viewex.core.secutity.Principal

class UiLayoutMap<PrincipalType : Principal<*, *>> {

    private val layoutMap: MutableMap<ViewId, UiLayout<PrincipalType>> = mutableMapOf()

    val allLayouts: Set<UiLayout<PrincipalType>> get() = layoutMap.values.toSet()

    operator fun get(
        rootId: ViewId
    ): UiLayout<PrincipalType> = getOrNull(rootId)
        ?: throw IllegalStateException("Layout with root id [ $rootId ] not found")

    fun getOrNull(rootId: ViewId): UiLayout<PrincipalType>? = layoutMap[rootId]

    fun add(layout: UiLayout<PrincipalType>) {
        val rootId = layout.rootView.viewId
        if (contains(rootId))
            throw IllegalStateException("Layout with root view id [ $rootId ] already exists")
        layoutMap[rootId] = layout
    }

    fun remove(rootId: ViewId) = layoutMap.remove(rootId).let {  }

    fun contains(rootId: ViewId): Boolean = layoutMap.containsKey(rootId)
}
