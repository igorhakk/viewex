package app.viewex.core.type

import app.viewex.core.util.ConvertUtils

abstract class NumberMapper<T : Number>(
    private val minValue: T,
    private val maxValue: T
) : ValueMapper<T> {

    override fun mapValue(src: Any): T =
        ConvertUtils.toNumber(src)?.let {
            val value = it.toDouble()
            if (value < minValue.toDouble() || value > maxValue.toDouble()) return@let null
            return@let convert(it)
        } ?: throw IllegalArgumentException(
            "Source value [ $src ] must be ${this::class.simpleName} and in the range [ min: $minValue, max: $maxValue ]"
        )

    protected abstract fun convert(src: Number): T
}
