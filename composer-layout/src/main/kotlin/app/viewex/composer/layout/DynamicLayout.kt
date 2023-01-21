package app.viewex.composer.layout

import app.viewex.composer.ManagedContent
import app.viewex.composer.View
import app.viewex.composer.ViewContext

interface DynamicLayout<Request : LayoutRequest> : Layout<Request> {
    fun getContent(context: ViewContext): ManagedContent<View, *>

}
