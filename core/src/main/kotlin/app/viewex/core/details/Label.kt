package app.viewex.core.details

import app.viewex.core.type.StringType
import app.viewex.core.util.StringUtils

class Label private constructor(
    label: Any? = null
) : StringType(label, 0, 50, StringUtils.Pattern.HtmlAllow) {

    companion object {

        val Empty = Label()

        fun parseOrEmpty(label: Any?): Label = parseOrNull(label) ?: Empty

        fun parseOrNull(label: Any?): Label? = label?.let { parse(it) }

        fun parse(label: Any): Label = Label(label)
    }
}
