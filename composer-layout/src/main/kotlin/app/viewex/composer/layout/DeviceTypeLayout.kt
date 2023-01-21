package app.viewex.composer.layout

import app.viewex.composer.DeviceType

class DeviceTypeLayout<LayoutType : Layout<*>>(
    private val desktopLayout: LayoutType,
    private val mobileLayout: LayoutType = desktopLayout,
    private val tabletLayout: LayoutType = desktopLayout,
) {
    fun getLayout(deviceType: DeviceType) : LayoutType {
        return when(deviceType) {
            DeviceType.Desktop -> desktopLayout
            DeviceType.Mobile -> mobileLayout
            DeviceType.Tablet -> tabletLayout
            else -> throw IllegalStateException("Unknown deviceType [ $deviceType ]")
        }
    }
}
