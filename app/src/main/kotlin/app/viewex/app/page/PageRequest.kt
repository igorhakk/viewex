package app.viewex.app.page

import app.viewex.composer.layout.LayoutParams
import app.viewex.composer.layout.LayoutRequest
import app.viewex.core.type.UrlPath

class PageRequest(
    override val params: LayoutParams,
    val extensionPath: UrlPath
) : LayoutRequest
