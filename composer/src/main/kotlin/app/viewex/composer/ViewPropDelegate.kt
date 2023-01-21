package app.viewex.composer

import kotlin.reflect.KProperty

class ViewPropDelegate<Val>(
    private var mutableProps: ViewProps.Mutable,
    private var value: Val
) {
    operator fun getValue(
        thisRef: View,
        property: KProperty<*>
    ): Val = value

    operator fun setValue(
        thisRef: View,
        property: KProperty<*>,
        value: Val
    ) {
        if (value == null)
            mutableProps.remove(property.name)
        else
            mutableProps[property.name] = value

        this.value = value
    }

}
