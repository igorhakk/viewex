package app.viewex.core.type

import app.viewex.core.util.ConvertUtils
import kotlin.reflect.KClass

interface PathType<Item : PathType.Item> : ValueType.String {

    companion object {
        const val Separator = "."
    }

    val items: List<Item>

    interface Item : ValueType.String

    abstract class Abstract<Item : PathType.Item>(
        items: Iterable<Item>,
        private val separator: String = Separator
    ) : PathType<Item> {

        override val rawType: KClass<String> = String::class

        final override val items: List<Item> = items.onEach { item ->
            if (item.isEmpty) throw EmptyValueException(item::class)
        }.toList()

        final override val isEmpty: Boolean = this.items.isEmpty()

        override val value: String
            get() {
                if (isEmpty) throw EmptyValueException(this::class)
                return items.joinToString(separator)
            }

        override val valueOrNull: String? = ConvertUtils.tryOrNull { value }

        override fun toString(): String = defaultToString()

        override fun equals(other: Any?): Boolean = defaultEquals(other)

        override fun hashCode(): Int = defaultHashCode()
    }
}
