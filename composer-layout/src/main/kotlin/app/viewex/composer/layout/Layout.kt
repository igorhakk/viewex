package app.viewex.composer.layout

import app.viewex.composer.ViewTemplate

interface Layout<Request : LayoutRequest> {
    fun getTemplate(request: Request) : ViewTemplate
}
