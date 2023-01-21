package app.viewex.ui.internal

import app.viewex.composer.Managed
import app.viewex.composer.ViewId
import app.viewex.composer.action.ViewAction
import app.viewex.composer.event.EventData
import app.viewex.composer.event.EventName
import app.viewex.ui.UiContext
import app.viewex.ui.message.ViewActionMessage
import app.viewex.ui.message.ViewEventListener

class ActionPublisher {
    private val attachedView: MutableSet<ViewId> = mutableSetOf()

    private val pendingActions: MutableSet<ViewAction<*>> = mutableSetOf()

    private var context: UiContext<*>? = null
    fun attached(viewId: ViewId) = attachedView.contains(viewId)

    fun publish(action: ViewAction<*>) {
        if (attachedView.contains(action.viewId))
            return publishAction(action)
        pendingActions.add(action)
    }

    fun init(context: UiContext<*>) {
        this.context = context
        context.session.addReceiveMessageListener(AttachMessageListener())
    }

    private fun publishAction(action: ViewAction<*>) {
        context?.session?.send(ViewActionMessage(action))
            ?: throw IllegalStateException("Action publisher not initialized")
    }

    inner class AttachMessageListener : ViewEventListener() {

        override fun handleEvent(name: EventName, data: EventData) {

            val viewId = ViewId.parseOrNull(data[ViewId.ParamName])
                ?: throw IllegalArgumentException(
                    "Param ${ViewId.ParamName} not found in event data. Event [ name: $name, data: $data ]"
                )

            when (name) {
                Managed.OnAttachedViewEventName -> onAttach(viewId)
                Managed.OnDetachedViewEventName -> onDetach(viewId)
            }
        }

        private fun onAttach(viewId: ViewId) {
            attachedView.add(viewId)

            pendingActions.forEach {
                if (it.viewId == viewId) {
                    publishAction(it)
                    pendingActions.remove(it)
                }

            }
        }

        private fun onDetach(viewId: ViewId) {
            pendingActions.forEach {
                if (it.viewId == viewId)
                    pendingActions.remove(it)
            }

            attachedView.remove(viewId)
        }

    }
}
