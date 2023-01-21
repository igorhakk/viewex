package app.viewex.composer

import app.viewex.composer.action.ActionName
import app.viewex.composer.action.ViewAction
import app.viewex.composer.event.BasicEventListener
import app.viewex.composer.event.EventHandler
import app.viewex.composer.event.EventListener
import app.viewex.composer.event.EventName

interface Managed :  View {

    companion object {
        val OnAttachedViewEventName = EventName.Basic("onAttachedView")
        val OnDetachedViewEventName = EventName.Basic("onDetachedView")
        val OnUpdatedEventName = EventName.Basic("onUpdatedView")
    }

    fun attached(context: ViewContext) = context.attached(viewId)

    fun addOnAttachedListener(
        context: ViewContext,
        handler: EventHandler.Basic
    ): EventListener = BasicEventListener(
        OnAttachedViewEventName.identifyView(viewId),
        handler
    ).also {
        context.registerListener(it)
    }

    fun addOnDetachedListener(
        context: ViewContext,
        handler: EventHandler.Basic
    ): EventListener = BasicEventListener(
        OnDetachedViewEventName.identifyView(viewId),
        handler
    ).also {
        context.registerListener(it)
    }

    fun addOnUpdatedViewListener(
        context: ViewContext,
        handler: EventHandler.Basic
    ): EventListener = BasicEventListener(
        OnUpdatedEventName.identifyView(viewId),
        handler
    ).also {
        context.registerListener(it)
    }

    fun updateView(context: ViewContext) {
        if (!context.attached(viewId))
            throw IllegalStateException("View [ ${getTemplate()} ] has not been attached yet")
        UpdateViewAction(
            viewId,
            getTemplate()
        ).also { context.callAction(it) }
    }


    class UpdateViewAction(
        viewId: ViewId,
        template: ViewTemplate
    ) : ViewAction<ViewTemplate>(viewId, Name, template) {

        companion object {
            val Name = ActionName("updateView")
        }
    }

}
