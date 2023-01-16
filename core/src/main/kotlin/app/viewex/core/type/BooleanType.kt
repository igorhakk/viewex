package app.viewex.core.type

import kotlin.reflect.KClass

open class BooleanType protected constructor(
    boolean: Any?,
    mapper: ValueMapper<Boolean>
) : ValueType.Boolean, AbstractType<Boolean>(boolean, mapper) {

    constructor(boolean: Any) : this(boolean, Mapper())

    companion object {
        val Empty = BooleanType(null, ValueMapper.Empty())
    }


    final override val rawType: KClass<Boolean> = Boolean::class

    private class Mapper : ValueMapper<Boolean> {

        override fun mapValue(
            src: Any
        ): Boolean = when (src.toString()) {
            "true", "1" -> true
            "false", "0" -> false
            else -> throw IllegalArgumentException(
                "Source value [ $src ] must be as true, 1 or false, 0"
            )
        }
    }
}
