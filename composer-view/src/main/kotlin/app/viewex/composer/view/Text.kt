package app.viewex.composer.view

import app.viewex.composer.Color
import app.viewex.composer.Size
import app.viewex.composer.ViewContent
import app.viewex.composer.ViewContext
import app.viewex.core.util.clearHtml

class Text(context: ViewContext) : AbstractView<Text.Content>(context) {

    var textColor: Color? by viewProp(null)

    var textSize: Size? by viewProp(null)

    var text: String? = null
        set(value) {
            setContent(Content(value))
            field = value
        }

    class Content(
        private val text: String?
    ) : ViewContent, CharSequence {

        companion object {
            val Empty = Content(null)

            fun clearedHtml(text: String): Content = Content(text.clearHtml())
        }

        override val length: Int = text?.length ?: 0

        override fun get(
            index: Int
        ): Char = text?.get(index) ?: throw NullPointerException("Text is null")

        override fun subSequence(
            startIndex: Int,
            endIndex: Int
        ): CharSequence = text?.subSequence(startIndex, endIndex)
            ?: throw NullPointerException("Text is null")

    }
}
