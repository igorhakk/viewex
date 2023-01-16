package app.viewex.core.type

open class PhoneNumberType protected constructor(
    number: Any? = null,
    minLength: Int = MinLength,
    maxLength: Int = MaxLength
) : StringType(number, minLength, maxLength, "[0-9]*"){

    companion object {
        const val MinLength = 7
        const val MaxLength = 16

        val Empty = PhoneNumberType()

        fun parseOrEmpty(
            number: Any?,
            minLength: Int = MinLength,
            maxLength: Int = MaxLength
        ) : PhoneNumberType = number?.let { parse(number, minLength, maxLength) } ?: Empty


        fun parseOrNull(
            number: Any?,
            minLength: Int = MinLength,
            maxLength: Int = MaxLength
        ) : PhoneNumberType? = number?.let { parse(it) }

        fun parse(
            number: Any,
            minLength: Int = MinLength,
            maxLength: Int = MaxLength
        ) : PhoneNumberType = PhoneNumberType(number, minLength, maxLength)
    }
}
