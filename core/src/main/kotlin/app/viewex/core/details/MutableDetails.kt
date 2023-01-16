package app.viewex.core.details

import app.viewex.core.util.StringUtils
import app.viewex.core.util.firstToUpper

abstract class MutableDetails<Name : ObjectName, Details : ObjectDetails>(
    name: Name? = null
) {
    private var label: Label = Label.parseOrEmpty(
        name?.let {
            StringUtils.camelCaseToString(it.value).firstToUpper()
        }
    )

    private var description: Description = Description.Empty

    private var icon: IconName = IconName.Empty

    fun setLabel(label: String) {
        this.label = Label.parse(label)
    }

    fun setDescription(description: String) {
        this.description = Description.parse(description)
    }

    fun setIcon(icon: IconName) {
        this.icon = icon
    }

    fun getDetails(): Details = mapDetails(label, description, icon)

    protected abstract fun mapDetails(
        label: Label,
        description: Description,
        icon: IconName
    ): Details
}
