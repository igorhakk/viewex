package app.viewex.core.details

import app.viewex.core.type.EmptyValueException
import app.viewex.core.type.StringType
import app.viewex.core.type.ValueType
import app.viewex.core.util.ConvertUtils
import java.net.URL
import kotlin.reflect.KClass

interface IconName : ValueType.String {

    object Empty : IconName {
        override val rawType: KClass<String> = String::class
        override val value: String get() = throw EmptyValueException(IconName::class)
        override val isEmpty: Boolean = true
    }

    companion object {

        fun parseOrNull(raw: String): IconName? = ConvertUtils.tryOrNull { parse(raw) }

        fun parseOrEmpty(raw: String): IconName = parseOrNull(raw) ?: Empty

        fun parse(raw: String): IconName {
            if (Image.Pattern.toRegex().matches(raw)) return Image.parseOrEmpty(raw)
            if (Font.Pattern.toRegex().matches(raw)) return Font.parseOrEmpty(raw)
            throw IllegalArgumentException("Raw value [ $raw ] is not IconName")
        }


    }

    class Image private constructor(
        imgUrl: String?
    ) : IconName, StringType(imgUrl, 7, 150, Pattern) {

        constructor(url: URL) : this("img:${url.path}")

        companion object {
            const val Pattern = "^(img:http:\\/\\/|img:https:\\/\\/)[\\w\\/\\.\\-].{10,}"

            val Empty = Image(null)


            fun parseOrEmpty(imgUrl: String?): Image = parseOrNull(imgUrl) ?: Empty

            fun parseOrNull(imgUrl: String?): Image? = imgUrl?.let { parse(it) }

            fun parse(
                imgUrl: String
            ): Image = imgUrl.removePrefix("img:").let { Image("img:$it}") }
        }
    }

    class Font private constructor(
        name: String?
    ) : IconName, StringType(name, 1, 50, Pattern) {

        companion object {
            const val Pattern = "^(?!img:http:\\/\\/|img:https:\\/\\/)\\w*"

            val Empty = Font(null)

            fun parseOrEmpty(name: String?): Font = parseOrNull(name) ?: Empty

            fun parseOrNull(name: String?): Font? = name?.let { parse(it) }

            fun parse(name: String): Font = Font(name)
        }
    }

}
