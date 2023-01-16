package app.viewex.core.type

import kotlin.reflect.KClass

open class StringType protected constructor(
    string: Any?,
    mapper: ValueMapper<String>
) : ValueType.String, AbstractType<String>(string, mapper) {

    constructor(
        value: Any?,
        minLength: Int,
        maxLength: Int,
        pattern: String
    ) : this(value, Mapper(minLength, maxLength, pattern))

    companion object {
        val Empty = StringType(null, ValueMapper.Empty())
    }

    final override val rawType: KClass<String> = String::class

    private class Mapper(
        private val minLength: Int,
        private val maxLength: Int,
        private val pattern: String
    ) : ValueMapper<String> {

        override fun mapValue(src: Any): String {
            return src.toString().trim().let {
                val invalidMatching = it.isNotEmpty() && !pattern.toRegex().matches(it)
                if (it.length < minLength || it.length > maxLength || invalidMatching) return@let null
                return@let it
            } ?: throw IllegalArgumentException(
                "Source value [ $src ] must match the params: pattern: $pattern, length [ min: $minLength, max: $maxLength ]"
            )
        }
    }

}
