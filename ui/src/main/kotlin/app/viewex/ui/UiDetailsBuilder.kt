package app.viewex.ui

import app.viewex.app.localization.TranslateService
import app.viewex.composer.layout.details.LayoutName
import app.viewex.composer.layout.details.LocalizedDetails
import app.viewex.composer.layout.details.MessageGroup

class UiDetailsBuilder(
    name: LayoutName,
    translateService: TranslateService
) : LocalizedDetails.Builder(MessageGroup(MessageGroup.Type.Ui, name), translateService)
