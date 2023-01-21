package app.viewex.composer.view

import app.viewex.composer.Resizable
import app.viewex.composer.View
import app.viewex.composer.ViewId

class ResizedView private constructor(viewId: ViewId?) : AbstractView.ContentView<ResizedView>(viewId), Resizable {

    constructor() : this(null)

    override val self: ResizedView = this

    override fun cloneWithContent(
        content: View?
    ): ResizedView = ResizedView(viewId).setContent(content)

}
