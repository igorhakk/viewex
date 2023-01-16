package app.viewex.composer.event.mapper

import app.viewex.composer.ScreenSize
import app.viewex.composer.Size
import app.viewex.composer.event.EventData
import app.viewex.composer.event.EventDataMapper
import app.viewex.core.type.EmptyValueException

object ScreenSizeDataMapper : EventDataMapper<ScreenSize> {

    override fun map(data: EventData): ScreenSize {
        val width = Size.Px.parseOrNull(data[ScreenSize.WidthParam])
            ?: throw EmptyValueException(Size.Px::class, ScreenSize.WidthParam)

        val height = Size.Px.parseOrNull(data[ScreenSize.HeightParam])
            ?: throw EmptyValueException(Size.Px::class, ScreenSize.HeightParam)

        return ScreenSize(width, height)
    }
}
