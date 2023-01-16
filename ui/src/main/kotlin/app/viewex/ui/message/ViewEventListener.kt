package app.viewex.ui.message

import app.viewex.composer.event.EventData
import app.viewex.composer.event.EventName
import org.slf4j.LoggerFactory

abstract class ViewEventListener : ReceiveMessageListener {

    companion object {
        val MessageType = UiMessage.Type("viewEvent")
    }

    final override val messageType: UiMessage.Type = MessageType

    private val log = LoggerFactory.getLogger(this::class.java)

    final override fun handle(data: UiMessage.DataParams) {
        try {
            val eventName = data[EventName.ParamName]?.let {
                EventName.parse(it)
            } ?: throw IllegalArgumentException(
                "Illegal event name [ ${data[EventName.ParamName]} ] in ui message"
            )

            val eventData = EventData.parse(data[EventData.ParamName])

            handleEvent(eventName, eventData)
        } catch (e: Exception) {
            log.warn("Parse view event data error", e)
        }
    }

    protected abstract fun handleEvent(name: EventName, data: EventData)

}
