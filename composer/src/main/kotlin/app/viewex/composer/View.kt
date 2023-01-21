package app.viewex.composer

interface View : IdentifiedView {

    object Blank : View {
        override fun getTemplate(): ViewTemplate = ViewTemplate(viewId, TemplateName.Blank)

        override val viewId: ViewId = ViewId.generate(this::class)

    }

    fun getTemplate(): ViewTemplate


}
