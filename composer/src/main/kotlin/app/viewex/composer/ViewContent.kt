package app.viewex.composer

interface ViewContent {

    companion object {
        val Empty: ViewContent = object : ViewContent {
            override fun toString(): String = "empty"
        }
    }

    class Template(template: ViewTemplate) : ViewTemplate(
        template.viewId,
        template.template,
        template.props,
        template.content
    ), ViewContent {

        companion object {
            fun of(view: View?): ViewContent {
                if (view == null) return ViewContent.Empty
                return Template(view.getTemplate())
            }
        }
    }
}
