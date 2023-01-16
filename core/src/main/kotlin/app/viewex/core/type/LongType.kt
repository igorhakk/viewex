package app.viewex.core.type

import kotlin.reflect.KClass

open class LongType protected constructor(
    long: Any?,
    mapper: ValueMapper<Long>
) : ValueType.Long, AbstractType<Long>(long, mapper) {

    protected constructor(
        long: Any?,
        minValue: Long = Long.MIN_VALUE,
        maxValue: Long = Long.MAX_VALUE
    ) : this(long, Mapper(minValue, maxValue))

    companion object {
        val Empty = LongType(null, ValueMapper.Empty())

        fun parseOrEmpty(
            long: Any?,
            minValue: Long = Long.MIN_VALUE,
            maxValue: Long = Long.MAX_VALUE
        ): LongType = parseOrNull(long, minValue, maxValue) ?: Empty

        fun parseOrNull(
            long: Any?,
            minValue: Long = Long.MIN_VALUE,
            maxValue: Long = Long.MAX_VALUE
        ): LongType? = long?.let { parse(long, minValue, maxValue) }

        fun parse(
            long: Any,
            minValue: Long = Long.MIN_VALUE,
            maxValue: Long = Long.MAX_VALUE
        ): LongType = LongType(long, minValue, maxValue)
    }

    final override val rawType: KClass<Long> = Long::class

    private class Mapper(
        minValue: Long,
        maxValue: Long
    ) : NumberMapper<Long>(minValue, maxValue) {
        override fun convert(src: Number): Long = src.toLong()
    }
}
