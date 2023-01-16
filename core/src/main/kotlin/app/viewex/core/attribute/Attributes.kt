package app.viewex.core.attribute

class Attributes(
    attributes: Iterable<Pair<String, Any?>> = emptyList()
) : AttributeAccessor {

    companion object {

        val Empty = Attributes()

    }

    private val paramMap = attributes.map {
        Pair(it.first, it.second)
    }.associateBy({ it.first }, { it.second })

    override val names: Set<String> = paramMap.keys

    override fun get(name: String): Any? = paramMap[name]

    override fun contains(name: String): Boolean = paramMap.contains(name)

    override fun toString(): String = asString()


    class Mutable(params: Iterable<Pair<String, Any?>> = emptyList()) : AttributeAccessor.Mutable {

        private val params = Attributes(params).toMap().toMutableMap()

        override val names: Set<String> get() = params.keys

        fun add(name: String, value: Any?): Mutable = this.set(name, value).let { this }

        override operator fun set(name: String, value: Any?) {
            value?.also { params[name] = value }
        }

        override operator fun get(name: String): Any? = params[name]

        override operator fun contains(name: String): Boolean = params.containsKey(name)

        override fun toString(): String = asString()
    }
}

fun Attributes.toMutable(): Attributes.Mutable = Attributes.Mutable(this)
