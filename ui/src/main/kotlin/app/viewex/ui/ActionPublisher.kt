package app.viewex.ui

import app.viewex.composer.Lifecycle
import app.viewex.composer.ViewId
import app.viewex.composer.action.ViewAction
import app.viewex.composer.event.EventData
import app.viewex.composer.event.EventName
import app.viewex.ui.message.ViewActionMessage
import app.viewex.ui.message.ViewEventListener

class ActionPublisher(
    private val context: UiContext
) {
    private val attachedView: MutableSet<ViewId> = mutableSetOf()

    private val pendingActions: MutableSet<ViewAction<*>> = mutableSetOf()

    init {
        context.session.addReceiveMessageListener(AttachMessageListener())
    }

    fun isAttached(viewId: ViewId) = attachedView.contains(viewId)

    fun publish(action: ViewAction<*>) {
        if (attachedView.contains(action.viewId))
            return publishAction(action)
        pendingActions.add(action)
    }

    private fun publishAction(action: ViewAction<*>) {
        context.session.send(ViewActionMessage(action))
    }

    inner class AttachMessageListener : ViewEventListener() {

        override fun handleEvent(name: EventName, data: EventData) {

            val viewId = ViewId.parseOrNull(data[ViewId.ParamName])
                ?: throw IllegalArgumentException(
                    "Param ${ViewId.ParamName} not found in event data. Event [ name: $name, data: $data ]"
                )

            when (name) {
                Lifecycle.OnAttachedViewEventName -> onAttach(viewId)
                Lifecycle.OnDetachedViewEventName -> onDetach(viewId)
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
