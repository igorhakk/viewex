package app.viewex.composer


import app.viewex.composer.event.EventHandler
import app.viewex.composer.event.EventListener
import app.viewex.composer.event.EventName


interface Lifecycle {

    companion object {
        val OnAttachedViewEventName = EventName.Basic("onAttachedView")
        val OnDetachedViewEventName = EventName.Basic("onDetachedView")
        val OnUpdatedViewContentEventName = EventName.Basic("onUpdatedViewContent")
        val OnUpdatedViewPropEventName = EventName.Basic("onUpdatedViewProp")
    }

    val attached: Boolean

    fun addOnAttachedListener(handler: EventHandler.Basic): EventListener

    fun addOnDetachedListener(handler: EventHandler.Basic): EventListener

    fun addOnUpdatedContentListener(handler: EventHandler.Basic): EventListener

    fun addOnUpdatedPropListener(handler: EventHandler.Typed<ViewPropSet.Prop>): EventListener
}
