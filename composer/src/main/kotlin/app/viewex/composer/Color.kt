package app.viewex.composer

import app.viewex.core.type.StringType

class Color private constructor(
    color: String?
) : StringType(color, 7, 7, "^#\\w{6,6}") {
    companion object {
        val Empty = Color(null)

        fun parse(color: String) : Color = Color(color)
    }

}
