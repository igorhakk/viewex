package app.viewex.ui.internal

import app.viewex.composer.ContentHolder
import app.viewex.composer.View
import app.viewex.composer.ViewId

class DefaultContentHolder : ContentHolder {
    private val viewMap: MutableMap<ViewId, View> = mutableMapOf()

    override fun get(viewId: ViewId): View? = viewMap[viewId]

    override fun set(viewId: ViewId, content: View) {
        viewMap[viewId] = content
    }

    override fun contains(viewId: ViewId): Boolean = viewMap.containsKey(viewId)
}
