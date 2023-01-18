package app.viewex.app

import app.viewex.composer.layout.details.LayoutDetails
import app.viewex.composer.layout.details.LayoutName
import app.viewex.core.details.Description
import app.viewex.core.details.IconName
import app.viewex.core.details.Label
import app.viewex.core.type.UrlPath

class NavigateItem(
    val name: LayoutName,
    label: Label,
    description: Description,
    icon: IconName,
    val url: UrlPath,
    val order: Int
) : LayoutDetails(label, description, icon)
