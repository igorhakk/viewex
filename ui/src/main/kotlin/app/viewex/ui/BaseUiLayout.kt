package app.viewex.ui

import app.viewex.app.tmp.NavigateItem
import app.viewex.composer.View
import app.viewex.composer.layout.Layout
import app.viewex.composer.layout.LayoutRequest

interface BaseUiLayout : Layout<BaseUiLayout.Request> {

    interface Request : LayoutRequest {
        val navigateItems: Iterable<NavigateItem>
        val content: View?
    }

    object Proxy : BaseUiLayout {
        override fun getView(
            request: Request
        ): View = request.content ?: View.Blank
    }
}
