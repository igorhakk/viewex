package app.viewex.core.attribute

open class Attributes(
    attributes: Iterable<Pair<String, Any?>> = emptyList()
) : AttributeAccessor {

    companion object {
        val Empty = Attributes()
    }

    private val paramMap = attributes.map {
        Pair(it.first, it.second)
    }.associateBy({ it.first }, { it.second })

    final override val names: Set<String> get() = paramMap.keys

    final override fun get(name: String): Any? = paramMap[name]

    final override fun contains(name: String): Boolean = paramMap.contains(name)

    final override fun toString(): String = asString()


    open class Mutable(params: Iterable<Pair<String, Any?>> = emptyList()) : AttributeAccessor.Mutable {

        private val params = Attributes(params).toMap().toMutableMap()

        final override val names: Set<String> get() = params.keys

        fun add(name: String, value: Any?): Mutable = this.set(name, value).let { this }

        final override operator fun set(name: String, value: Any?) {
            value?.also { params[name] = value }
        }

        final override fun remove(name: String) = params.remove(name).let {  }

        final override operator fun get(name: String): Any? = params[name]

        final override operator fun contains(name: String): Boolean = params.containsKey(name)

        override fun toString(): String = asString()
    }
}

fun Attributes.toMutable(): Attributes.Mutable = Attributes.Mutable(this)
