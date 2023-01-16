package app.viewex.core.details


interface ObjectDetails {

    val label: Label

    val description: Description

    val icon: IconName

    companion object {

        val LabelItem = IdentifiedPath.Item.parse("label")

        val DescriptionItem = IdentifiedPath.Item.parse("description")

    }
}
