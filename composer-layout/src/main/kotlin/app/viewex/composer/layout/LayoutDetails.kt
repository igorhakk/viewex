package app.viewex.composer.layout

import app.viewex.core.details.Description
import app.viewex.core.details.IconName
import app.viewex.core.details.Label
import app.viewex.core.details.ObjectDetails
import app.viewex.core.type.UrlPath
import app.viewex.core.util.StringUtils
import app.viewex.core.util.firstToUpper

open class LayoutDetails(
    override val label: Label,
    override val description: Description = Description.Empty,
    override val icon: IconName = IconName.Empty
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

    class Route(
        label: Label,
        description: Description,
        icon: IconName,
        val url: UrlPath
    ) : LayoutDetails(label, description, icon) {

        constructor(otherDetails: ObjectDetails, url: UrlPath) : this(
            otherDetails.label,
            otherDetails.description,
            otherDetails.icon,
            url
        )

        companion object {

            fun ofLayoutName(
                name: LayoutName,
                url: UrlPath,
                iconName: IconName = IconName.Empty,
                description: String? = null
            ): Route = parse(
                StringUtils.camelCaseToString(name.value).firstToUpper(),
                url,
                description,
                iconName
            )

            fun parse(
                label: String,
                url: UrlPath,
                description: String? = null,
                icon: IconName = IconName.Empty
            ): Route = Route(
                Label.parse(label),
                Description.parseOrEmpty(description),
                icon,
                url
            )
        }
    }
}
