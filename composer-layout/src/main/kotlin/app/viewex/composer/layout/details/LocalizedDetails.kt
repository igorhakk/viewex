package app.viewex.composer.layout.details

import app.viewex.app.localization.TranslateService
import app.viewex.core.details.*
import app.viewex.core.util.StringUtils
import app.viewex.core.util.firstToUpper
import java.util.*

class LocalizedDetails(
    private val messageGroup: MessageGroup,
    private val translateService: TranslateService,
    private val defaultLabel: Label = Label.Empty,
    private val defaultDescription: Description = Description.Empty,
    private val icon: IconName = IconName.Empty
) : DetailsProvider<LayoutDetails> {


    override suspend fun getDetails(
        locale: Locale
    ): LayoutDetails {
        val source = translateService.getSource(messageGroup.groupPath, locale)

        val sourceLabel = source.getMessage(
            messageGroup.getMessagePath(ObjectDetails.LabelItem)
        )?.let { Label.parse(it) } ?: defaultLabel

        val sourceDescription = source.getMessage(
            messageGroup.getMessagePath(ObjectDetails.DescriptionItem)
        )?.let { Description.parse(it) } ?: defaultDescription

        return LayoutDetails(sourceLabel, sourceDescription, icon)
    }

    open class Builder(
        private val messageGroup: MessageGroup,
        private val translateService: TranslateService,
    ) {
        private var label: Label = Label.parse(
            StringUtils.camelCaseToString(
                messageGroup.layoutName.value
            ).firstToUpper()
        )

        private var description: Description = Description.Empty

        private var icon: IconName = IconName.Empty

        fun withDefaultLabel(label: String): Builder = Label.parse(label).let {
            this.label = it
            this
        }

        fun withDefaultDescription(text: Description): Builder = Description.parse(text).let {
            this.description = it
            this
        }

        fun withIcon(iconName: String): Builder = withIcon(IconName.parse(iconName))

        fun withIcon(icon: IconName): Builder {
            this.icon = icon
            return this
        }

        fun build(): LocalizedDetails = LocalizedDetails(messageGroup, translateService, label, description, icon)
    }


//    class Endpoint(
//        layoutName: LayoutName
//    ) : AbstractLocalizedDetails(MessageGroup(MessageGroup.Type.Endpoint, layoutName))
//
//    class Ui(
//        layoutName: LayoutName
//    ) : AbstractLocalizedDetails(MessageGroup(MessageGroup.Type.Ui, layoutName))
//
//    class Page(
//        layoutName: LayoutName
//    ) : AbstractLocalizedDetails(MessageGroup(MessageGroup.Type.Page, layoutName))


}
