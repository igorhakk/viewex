package app.viewex.composer

import app.viewex.core.type.StringType
import app.viewex.core.type.ValueType

interface Size : ValueType.String {

    object Empty : Size, StringType(null, 0, 0, "")

    object None : Size, StringType("none", 4, 4, "none")

    object Inherit : Size, StringType("inherit", 7, 7, "inherit")

    class Px(
        intValue: Int
    ) : Size, StringType(intValue.toString().plus("px"), 4, 8, Pattern) {

        companion object {
            val Pattern = "^-{0,1}[0-9]{1,5}px$"

            fun parse(value: Any): Px = when (value) {
                is Number -> Px(value.toInt())
                is String -> Px(value.removeSuffix("px").toInt())
                else -> throw IllegalArgumentException("Illegal size px value [ $value ]")
            }

            fun parseOrNull(value: Any?) = value?.let { parse(it) }

        }
    }

    class Em(
        val intValue: Int
    ) : Size, StringType(intValue.toString().plus("em"), 4, 8, Pattern) {
        companion object {
            val Pattern = "^-{0,1}[0-9]{1,5}em$"
        }
    }

    class Rem(
        val intValue: Int
    ) : Size, StringType(intValue.toString().plus("rem"), 5, 9, Pattern) {
        companion object {
            val Pattern = "^-{0,1}[0-9]{1,5}rem$"
        }
    }

    class Pt(
        val intValue: Int
    ) : Size, StringType(intValue.toString().plus("pt"), 4, 8, Pattern) {
        companion object {
            val Pattern = "^-{0,1}[0-9]{1,5}pt$"
        }
    }

    class Percent(
        val intValue: Int
    ) : Size, StringType(intValue.toString().plus("%"), 3, 7, Pattern) {
        companion object {
            val Pattern = "^-{0,1}[0-9]{1,5}\\%$"
        }
    }
}
