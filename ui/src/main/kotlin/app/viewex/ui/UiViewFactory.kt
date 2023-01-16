package app.viewex.ui

fun interface UiViewFactory {
    fun create(uiContext: UiContext): UiView
}
