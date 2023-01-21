package app.viewex.composer

interface ContentHolder {
    operator fun get(viewId: ViewId): View?
    operator fun set(viewId: ViewId, content: View)
    fun contains(viewId: ViewId): Boolean
}
