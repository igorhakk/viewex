package app.viewex.core.type

import kotlin.reflect.KClass

open class IntType protected constructor(
    int: Any?,
    mapper: ValueMapper<Int>
) : ValueType.Int, AbstractType<Int>(int, mapper) {

    protected constructor(
        int: Any? = null,
        minValue: Int = Int.MIN_VALUE,
        maxValue: Int = Int.MAX_VALUE
    ) : this(int, Mapper(minValue, maxValue))

    companion object {
        val Empty = IntType(null, ValueMapper.Empty())

        fun parseOrEmpty(
            int: Any?,
            minValue: Int = Int.MIN_VALUE,
            maxValue: Int = Int.MAX_VALUE
        ): IntType = parseOrNull(int, minValue, maxValue) ?: Empty

        fun parseOrNull(
            int: Any?,
            minValue: Int = Int.MIN_VALUE,
            maxValue: Int = Int.MAX_VALUE
        ): IntType? = int?.let { parse(int, minValue, maxValue) }

        fun parse(
            int: Any,
            minValue: Int = Int.MIN_VALUE,
            maxValue: Int = Int.MAX_VALUE
        ): IntType = IntType(int, minValue, maxValue)
    }

    final override val rawType: KClass<Int> = Int::class

    private class Mapper(
        minValue: Int,
        maxValue: Int
    ) : NumberMapper<Int>(minValue, maxValue) {
        override fun convert(src: Number): Int = src.toInt()
    }

}
