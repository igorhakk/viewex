package app.viewex.composer.view

import app.viewex.composer.*


class Flex(
    context: ViewContext,
    flexInit: (Flex.() -> Unit)? = null
) : AbstractView<Flex.Content>(context), FlexProps {

    private val theme = context.theme

    private val themeProps = theme.getDefaultProps(FlexProps::class)

    override var direction: Direction? by viewProp(themeProps?.direction)

    override var wrap: Wrap? by viewProp(themeProps?.wrap)

    override var justifyContent: JustifyContent? by viewProp(themeProps?.justifyContent)

    override var alignItems: AlignItems? by viewProp(themeProps?.alignItems)

    override var alignContent: AlignContent? by viewProp(themeProps?.alignContent)

    override var height: Size? by viewProp(themeProps?.height)

    override var width: Size? by viewProp(themeProps?.width)

    override var marginTop: Size? by viewProp(themeProps?.marginTop)

    override var marginBottom: Size? by viewProp(themeProps?.marginBottom)

    override var marginLeft: Size? by viewProp(themeProps?.marginLeft)

    override var marginRight: Size? by viewProp(themeProps?.marginRight)

    override var paddingTop: Size? by viewProp(themeProps?.paddingTop)

    override var paddingBottom: Size? by viewProp(themeProps?.paddingBottom)

    override var paddingLeft: Size? by viewProp(themeProps?.paddingLeft)

    override var paddingRight: Size? by viewProp(themeProps?.paddingRight)

    private val children = mutableListOf<View>()

    init {
        if (flexInit != null) this.flexInit()
    }


    fun addChild(view: () -> View) = addChild(view())

    fun addChild(vararg view: View) = addChildren(view.toList())

    fun replaceChildren(views: Iterable<View>) {
        children.clear()
        addChildren(views)
    }

    fun addChildren(views: Iterable<View>) {
        children.addAll(views)
        setContent(Content(children.map { it.getTemplate() }))
    }

    enum class Direction : EnumProp { //def Row
        Row, RowReverse, Column, ColumnReverse
    }

    enum class Wrap : EnumProp { //def Nowrap
        Nowrap, Wrap, WrapReverse
    }

    enum class JustifyContent : EnumProp { //def FlexStart
        FlexStart, FlexEnd, Center, SpaceBetween, SpaceAround, SpaceEvenly
    }

    enum class AlignItems : EnumProp { //def Stretch
        Stretch, FlexStart, FlexEnd, Center, Baseline
    }

    enum class AlignContent : EnumProp { //def Normal
        Normal, FlexStart, FlexEnd, Center, SpaceBetween, SpaceAround, Stretch
    }

    fun Flex.flexChild(
        flexInit: Flex.() -> Unit
    ) = addChild(Flex(this.context).also(flexInit))

    class Content(
        views: Iterable<ViewTemplate>?
    ) : ViewContent, Iterable<ViewTemplate> {

        private val _views = views?.toList() ?: emptyList()

        companion object {
            val Empty = Content(null)
        }

        override fun iterator(): Iterator<ViewTemplate> = _views.iterator()

        override fun toString(): String =
            "FlexContent [ ${viewsToString()} ]"

        private fun viewsToString(): String {
            if (_views.isEmpty()) return " empty "
            return _views.joinToString(", ") {
                        it.toString()
                    }
        }
    }
}

interface FlexProps : ViewProps {
    var direction: Flex.Direction?
    var wrap: Flex.Wrap?
    var justifyContent: Flex.JustifyContent?
    var alignItems: Flex.AlignItems?
    var alignContent: Flex.AlignContent?
    var height: Size?
    var width: Size?
    var marginTop: Size?
    var marginBottom: Size?
    var marginLeft: Size?
    var marginRight: Size?
    var paddingTop: Size?
    var paddingBottom: Size?
    var paddingLeft: Size?
    var paddingRight: Size?
}
