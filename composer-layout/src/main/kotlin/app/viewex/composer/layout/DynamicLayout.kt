package app.viewex.composer.layout

import app.viewex.composer.Layout
import app.viewex.composer.View

interface DynamicLayout : Layout {

    fun setDefaultContent(content: View)

    fun updateContent(content: View)
}
