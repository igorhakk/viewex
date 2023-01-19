package app.viewex.composer.layout

import app.viewex.composer.View

interface DynamicLayout<Request : LayoutRequest> : Layout<Request> {

    fun setDefaultContent(content: View)

    fun updateContent(content: View)
}
