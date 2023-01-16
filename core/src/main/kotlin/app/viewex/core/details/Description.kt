package app.viewex.core.details

import app.viewex.core.type.StringType
import app.viewex.core.util.StringUtils

class Description private constructor(
    text: Any? = null
) : StringType(text, 0, 50, StringUtils.Pattern.HtmlAllow) {

    companion object {
        val Empty = Description()

        fun parseOrEmpty(text: Any?): Description = parseOrNull(text) ?: Empty

        fun parseOrNull(text: Any?): Description? = text?.let { parse(it) }

        fun parse(text: Any): Description = Description(text)
    }
}
