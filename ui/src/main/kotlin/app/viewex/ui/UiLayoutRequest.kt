package app.viewex.ui

import app.viewex.composer.Route
import app.viewex.composer.layout.LayoutParams
import app.viewex.composer.layout.LayoutRequest
import app.viewex.core.type.UrlPath

class UiLayoutRequest(
    val urlPath: UrlPath,
    params: LayoutParams
) : LayoutRequest(params) {

    companion object {
        fun of(
            route: Route
        ): UiLayoutRequest = UiLayoutRequest(route.path, LayoutParams(route.query))
    }

}
