package app.viewex.composer.theme

import app.viewex.composer.Color
import app.viewex.composer.ViewProps
import kotlin.reflect.KClass

interface Theme {

    val primaryColor: Color

    fun <Props : ViewProps> getDefaultProps(propsClass: KClass<out Props>) : Props?
}
