package app.viewex.composer.layout

import app.viewex.composer.View

interface ContentLayout : Layout<ContentLayout.Request> {

    open class Request(
        val content: View?,
        params: LayoutParams = LayoutParams.Empty
    ) : LayoutRequest(params)

    object Proxy : ContentLayout {
        override fun getView(
            request: Request
        ): View = request.content ?: View.Blank
    }
}
