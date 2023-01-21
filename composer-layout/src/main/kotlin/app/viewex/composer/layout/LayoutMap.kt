package app.viewex.composer.layout

import app.viewex.composer.layout.details.LayoutName

interface LayoutMap<Layout : NamedLayout> {

    val allLayouts: Collection<Layout>

    operator fun get(name: LayoutName): Layout

    fun getOrNull(name: LayoutName): Layout?

    operator fun get(name: String): Layout

    fun getOrNull(name: String): Layout?

    fun contains(name: LayoutName): Boolean

    fun contains(name: String): Boolean
}
