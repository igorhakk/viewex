package app.viewex.composer.layout.details

import app.viewex.core.details.Description
import app.viewex.core.details.IconName
import app.viewex.core.details.Label
import app.viewex.core.details.ObjectDetails
import app.viewex.core.util.StringUtils
import app.viewex.core.util.firstToUpper

open class LayoutDetails(
    final override val label: Label,
    final override val description: Description = Description.Empty,
    final override val icon: IconName = IconName.Empty
) : ObjectDetails {

    constructor(otherDetails: ObjectDetails) : this(
        otherDetails.label,
        otherDetails.description,
        otherDetails.icon
    )

    companion object {

        fun ofLayoutName(
            name: LayoutName,
            iconName: IconName = IconName.Empty,
            description: String? = null
        ): LayoutDetails = parse(
            StringUtils.camelCaseToString(name.value).firstToUpper(),
            description,
            iconName
        )

        fun parse(
            label: String,
            description: String? = null,
            icon: IconName = IconName.Empty
        ): LayoutDetails = LayoutDetails(
            Label.parse(label),
            Description.parseOrEmpty(description),
            icon
        )
    }

}
