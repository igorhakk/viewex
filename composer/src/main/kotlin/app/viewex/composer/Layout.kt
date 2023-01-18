package app.viewex.composer

interface Layout {
    fun getTemplate(context: ViewContext, params: LayoutParams) : ViewTemplate
}
