package app.viewex.core.util

object StringUtils {

    object Pattern {

        private const val HtmlAllowSymbols = "А-Яа-я\\w\\s\\;\\,\\.\\-\\+\\*\\%\\\$\\#\\@\\!\\[\\]\\{\\}\\(\\)\\|"

        const val HtmlAllow = "[$HtmlAllowSymbols]*"

        const val HtmlForbidden = "[^$HtmlAllowSymbols]"

        const val Email = "^[A-Za-z0-9\\_\\-\\+]+(\\.[A-Za-z0-9\\-\\_]+)*" +
                "@[A-Za-z0-9\\-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})"

    }

    fun camelCaseToString(camelCase: String, divider: String = " "): String {
        val first = camelCase.firstOrNull()
        return first?.lowercase() + camelCase.substring(1).flatMap {
            if (it.isUpperCase()) {
                val list = divider.toList().toMutableList()
                list.add(it.lowercaseChar())
                return@flatMap list
            }
            return@flatMap listOf(it)
        }.joinToString(separator = "").trim()
    }

    fun stringToCamelCase(string: String, divider: String = " ", allLowerCase: Boolean = true): String {
        val split = string.split(divider)
        return split.map {
            val item = if (allLowerCase) it.lowercase() else it
            item.firstToUpper()
        }.joinToString("").trim()
    }

}

fun CharSequence.firstToUpper(): String = this.first().uppercase().plus(this.substring(1))

fun CharSequence.firstToLower(): String = this.first().lowercase().plus(this.substring(1))

fun CharSequence.clearHtml(): String = this.replace(StringUtils.Pattern.HtmlForbidden.toRegex(), "")
