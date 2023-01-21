package app.viewex.composer.layout

import app.viewex.composer.View

interface Layout<Request : LayoutRequest> {
    fun getView(request: Request) : View

}
