package app.viewex.composer.view

import app.viewex.composer.ManagedView
import app.viewex.composer.ViewContext
import app.viewex.composer.ViewId
import kotlin.reflect.KProperty

class PropDelegate<Val>(
    private val context: ViewContext,
    private var viewId: ViewId,
    private var value: Val
) {
    operator fun getValue(
        thisRef: AbstractView<*>,
        property: KProperty<*>
    ): Val = value

    operator fun setValue(
        thisRef: AbstractView<*>,
        property: KProperty<*>,
        value: Val
    ) = context.callAction(
        ManagedView.UpdatePropAction(viewId, property.name, value)
    ).also { this.value = value }

}
