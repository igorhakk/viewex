package app.viewex.composer.view

import app.viewex.composer.Color
import app.viewex.composer.Size
import app.viewex.composer.ViewContent
import app.viewex.core.util.clearHtml

class Text() : AbstractView() {

    var textColor: Color? by viewProp(null)

    var textSize: Size? by viewProp(null)

    var text: String? = null

    override fun getContent(): ViewContent? = text?.let { Content.clearedHtml(it) }

    class Content private constructor(
        private val text: String
    ) : ViewContent, CharSequence {

        companion object {
            fun clearedHtml(text: String): Content = Content(text.clearHtml())
        }

        override val length: Int = text.length

        override fun get(
            index: Int
        ): Char = text[index]

        override fun subSequence(
            startIndex: Int,
            endIndex: Int
        ): CharSequence = text.subSequence(startIndex, endIndex)

    }

}
