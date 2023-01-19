package app.viewex.composer

import app.viewex.composer.action.ActionName
import app.viewex.composer.action.ViewAction
import app.viewex.composer.event.*

abstract class ManagedView(
    final override val context: ViewContext
) : IdentifiedView, Lifecycle {

    companion object {
        val UpdatePropViewActionName = ActionName("updateViewProp")
        val UpdateContentActionName = ActionName("updateViewContent")
    }

    final override val viewId: ViewId = ViewId.generate(this::class)

    private var _attached: Boolean = false

    final override val attached: Boolean get() = _attached

    init {
        addOnAttachedListener { _attached = true }
    }

    final override fun addOnAttachedListener(
        handler: EventHandler.Basic
    ): EventListener = BasicEventListener(
        Lifecycle.OnAttachedViewEventName,
        handler
    ).also {
        context.registerListener(it)
    }

    final override fun addOnDetachedListener(
        handler: EventHandler.Basic
    ): EventListener = BasicEventListener(
        Lifecycle.OnAttachedViewEventName,
        handler
    ).also {
        context.registerListener(it)
    }

    final override fun addOnUpdatedContentListener(
        handler: EventHandler.Basic
    ): EventListener = BasicEventListener(
        Lifecycle.OnUpdatedViewContentEventName,
        handler
    ).also {
        context.registerListener(it)
    }

    final override fun addOnUpdatedPropListener(
        handler: EventHandler.Typed<ViewPropSet.Prop>
    ): EventListener = MappedEventListener(
        Lifecycle.OnUpdatedViewPropEventName,
        handler
    ) { data ->
        val name = data["name"]?.toString()
            ?: throw EventDataMappingException("Require event prop [ name ] ")

        val value = data["value"]

        ViewPropSet.Prop(name, value)
    }.also {
        context.registerListener(it)
    }

    protected fun ManagedView.renderContent(
        content: ViewContent
    ) = context.callAction(
        UpdateContentAction(viewId, content)
    )

    protected fun ManagedView.updateProp(
        name: String,
        value: Any?
    ) = context.callAction(
        UpdatePropAction(viewId, ViewPropSet.Prop(name, value))
    )

    class UpdateContentAction(
        viewId: ViewId,
        content: ViewContent
    ) : ViewAction<ViewContent>(viewId, UpdateContentActionName, content)

    class UpdatePropAction(
        viewId: ViewId,
        prop: ViewPropSet.Prop,
    ) : ViewAction<ViewPropSet.Prop>(viewId, UpdatePropViewActionName, prop) {
        constructor(viewId: ViewId, name: String, value: Any?) : this(viewId, ViewPropSet.Prop(name, value))
    }

}
