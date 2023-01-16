package app.viewex.composer

import app.viewex.composer.theme.ThemeType
import java.util.*

interface ViewSession {

    val theme: ThemeType

    val deviceType: DeviceType

    val orientation : Orientation

    val screenSize : ScreenSize

    val locale: Locale
}
