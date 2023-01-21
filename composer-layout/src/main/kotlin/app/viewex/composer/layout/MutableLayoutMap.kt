package app.viewex.composer.layout

import app.viewex.composer.layout.details.LayoutName

class MutableLayoutMap<Layout : NamedLayout> : LayoutMap<Layout> {

    private val layoutMap: MutableMap<String, Layout> = mutableMapOf()

    override val allLayouts: Set<Layout> get() = layoutMap.values.toSet()

    fun addLayout(vararg layout: Layout) = addLayouts(layout.toList())

    fun addLayouts(layouts: Iterable<Layout>) {
        layouts.forEach {
            putLayout(it)
        }
    }

    private fun putLayout(layout: Layout) {
        if (contains(layout.name))
            throw IllegalStateException("Layout [ name: ${layout.name}, class: ${layout::class} ] already exists")
        layoutMap[layout.name.value] = layout
    }

    override fun get(name: LayoutName): Layout = get(name.value)

    override fun getOrNull(name: LayoutName): Layout? = getOrNull(name.value)

    override fun get(name: String): Layout = getOrNull(name)
        ?: throw IllegalStateException("Layout [ $name ] not found")

    override fun getOrNull(name: String): Layout? = layoutMap[name]

    override fun contains(name: LayoutName): Boolean = layoutMap.containsKey(name.value)

    override fun contains(name: String): Boolean = layoutMap.containsKey(name)
}
