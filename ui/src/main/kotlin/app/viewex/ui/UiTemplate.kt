package app.viewex.ui

import app.viewex.composer.IdentifiedView
import app.viewex.composer.ViewId
import app.viewex.composer.ViewTemplate

class UiTemplate(
    override val viewId: ViewId,
    val content: ViewTemplate?
) : IdentifiedView
