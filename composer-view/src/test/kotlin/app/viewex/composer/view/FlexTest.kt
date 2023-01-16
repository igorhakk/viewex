package app.viewex.composer.view

import app.viewex.composer.ViewContext
import app.viewex.composer.action.ViewAction
import app.viewex.composer.event.EventListener
import org.junit.jupiter.api.Test

internal class FlexTest {

    @Test
    fun test() {
        val context = object : ViewContext {

            override fun registerListener(listener: EventListener) = println(listener)

            override fun callAction(action: ViewAction) = println(action)

        }

        val flex = Flex(context) {
            direction = Flex.Direction.Row
            justifyContent = Flex.JustifyContent.FlexEnd
            flexChild(context) {
                direction = Flex.Direction.Column
                alignContent = Flex.AlignContent.FlexEnd
            }
            flexChild(context) {
                direction = Flex.Direction.ColumnReverse
                alignContent = Flex.AlignContent.FlexStart
            }
            flexChild(context) {
                flexChild(context) {
                    wrap = Flex.Wrap.WrapReverse
                }
            }
        }

        flex.direction = Flex.Direction.RowReverse

        val template = flex.getTemplate()

        println(template)
    }
}
