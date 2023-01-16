package app.viewex.composer.event

import app.viewex.core.util.ClassUtils
import org.slf4j.LoggerFactory

open class MappedEventListener<T>(
    override val name: EventName,
    private val handler: EventHandler.Typed<T>,
    private val mapper: EventDataMapper<T>,
) : EventListener {

    private val log = LoggerFactory.getLogger(MappedEventListener::class.java)

    override fun handle(data: EventData) = try {
        val typedData = mapper.map(data)
        handler.handle(typedData)
    } catch (e: Exception) {
        log.warn("Mapping event data error [ $data ] ", e)
    }

    override fun toString(): String = ClassUtils.memberPropsToString(this)
}
