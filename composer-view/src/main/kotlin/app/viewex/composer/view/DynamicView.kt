package app.viewex.composer.view

import app.viewex.composer.View
import app.viewex.composer.ViewId

class DynamicView private constructor(
    viewId: ViewId?
) : AbstractView.ContentView<DynamicView>() {

    constructor() : this(null)

    override val self: DynamicView = this

    override fun cloneWithContent(content: View?): DynamicView = DynamicView(viewId)
}
