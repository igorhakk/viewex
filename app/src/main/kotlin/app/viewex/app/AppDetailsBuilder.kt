package app.viewex.app

import app.viewex.app.localization.TranslateService
import app.viewex.composer.layout.details.LayoutName
import app.viewex.composer.layout.details.LocalizedDetails
import app.viewex.composer.layout.details.MessageGroup

class AppDetailsBuilder(
    appName: LayoutName,
    translateService: TranslateService,
) : LocalizedDetails.Builder(MessageGroup(MessageGroup.Type.App, appName), translateService)
