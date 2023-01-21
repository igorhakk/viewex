package app.viewex.composer.view

import app.viewex.composer.Size
import app.viewex.composer.View
import app.viewex.composer.ViewContent
import app.viewex.composer.ViewTemplate


class Flex(
    flexInit: (Flex.() -> Unit)? = null
) : AbstractView() {

    var direction: Direction? by viewProp(null)

    var wrap: Wrap? by viewProp(null)

    var justifyContent: JustifyContent? by viewProp(null)

    var alignItems: AlignItems? by viewProp(null)

    var alignContent: AlignContent? by viewProp(null)

    var height: Size? by viewProp(null)

    var width: Size? by viewProp(null)

    var marginTop: Size? by viewProp(null)

    var marginBottom: Size? by viewProp(null)

    var marginLeft: Size? by viewProp(null)

    var marginRight: Size? by viewProp(null)

    var paddingTop: Size? by viewProp(null)

    var paddingBottom: Size? by viewProp(null)

    var paddingLeft: Size? by viewProp(null)

    var paddingRight: Size? by viewProp(null)

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
    }

    override fun getContent(): ViewContent = Content(children)

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
    ) = addChild(Flex().also(flexInit))

    class Content(
        views: Iterable<View>?
    ) : ViewContent, Iterable<ViewTemplate> {

        private val _views = views?.map { it.getTemplate() } ?: emptyList()

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
