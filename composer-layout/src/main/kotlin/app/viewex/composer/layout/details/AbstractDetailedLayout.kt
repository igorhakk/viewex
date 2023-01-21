package app.viewex.composer.layout.details

import app.viewex.app.localization.TranslateService
import app.viewex.core.details.Description
import app.viewex.core.details.IconName
import app.viewex.core.details.Label
import app.viewex.core.details.ObjectDetails
import app.viewex.core.secutity.Principal
import app.viewex.core.util.StringUtils
import app.viewex.core.util.firstToUpper
import app.viewex.localization.MessagePath
import app.viewex.localization.plus
import java.util.*

abstract class AbstractDetailedLayout<PrincipalType : Principal<*, *>>(
    final override val layoutType: LayoutType,
    private val translateService: TranslateService,
    excludeNameSuffixes: List<String>,
    name: String?
) : DetailedLayout<PrincipalType> {

    final override val name: LayoutName = name?.let {
        LayoutName.parse(it)
    } ?: LayoutName.nameOfClass(this::class, *excludeNameSuffixes.toTypedArray())

    private var defaultLabel: Label = Label.parse(
        StringUtils.camelCaseToString(this.name.value).firstToUpper()
    )

    private var defaultDescription: Description = Description.Empty

    private var icon: IconName = IconName.Empty


    private val groupPath = MessagePath(layoutType.value, this.name.value)

    private var labelPath = groupPath.plus(ObjectDetails.LabelItem)

    private val descriptionPath = groupPath.plus(ObjectDetails.DescriptionItem)

    protected fun setDefaultLabel(label: String) = Label.parse(label).let {
        defaultLabel = it
    }

    protected fun setDefaultDescription(text: String) = Description.parse(text).let {
        defaultDescription = it
    }

    protected fun setIcon(iconName: String) = setIcon(IconName.parse(iconName))

    protected fun setIcon(icon: IconName) {
        this.icon = icon
    }

    final override suspend fun getDetails(locale: Locale): LayoutDetails {
        val source = translateService.getSource(groupPath, locale)

        val sourceLabel = source.getMessage(
            labelPath
        )?.let { Label.parse(it) } ?: defaultLabel

        val sourceDescription = source.getMessage(
            descriptionPath
        )?.let { Description.parse(it) } ?: defaultDescription

        return LayoutDetails(sourceLabel, sourceDescription, icon)
    }
}
