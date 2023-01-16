package app.viewex.core.type

import app.viewex.core.util.StringUtils

open class EmailType protected constructor(
    email: Any? = null
) : StringType(email, 6, 100, StringUtils.Pattern.Email) {

    companion object {
        val Empty = EmailType()

        fun parseOrEmpty(email: Any?): EmailType = parseOrNull(email) ?: Empty

        fun parseOrNull(email: Any?): EmailType? = email?.let { parse(email) }

        fun parse(email: Any): EmailType = EmailType(email)
    }
}
