package app.viewex.app

import app.viewex.composer.layout.details.LayoutName
import app.viewex.core.details.Named
import app.viewex.core.details.ObjectDetails
import app.viewex.core.type.UrlPath

interface NavigateItem : ObjectDetails, Named<LayoutName> {
    interface Single : NavigateItem {
        val url: UrlPath
    }

    interface Group : NavigateItem {
        val children: Iterable<Single>
    }
}
