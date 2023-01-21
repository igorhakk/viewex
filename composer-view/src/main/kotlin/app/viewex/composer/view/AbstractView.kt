package app.viewex.composer.view

import app.viewex.composer.*
import java.util.function.Supplier

abstract class AbstractView(
    viewId: ViewId? = null,
    templateName: String? = null
) : View, Managed {

    private val mutableProps = ViewProps.Mutable()

    final override val viewId: ViewId = viewId?.let {
        if (it.isEmpty) return@let null
        it
    } ?: ViewId.generate(this::class)

    private val _templateName: TemplateName = templateName?.let {
        TemplateName(it)
    } ?: TemplateName.ofDefinitionClass(this::class)

    final override fun getTemplate(): ViewTemplate = ViewTemplate(
        viewId,
        _templateName,
        mutableProps,
        getContent() ?: ViewContent.Empty
    )

    final override fun toString(): String = this::class.simpleName + "[ " + getTemplate().toString() + " ]"

    protected abstract fun getContent(): ViewContent?

    protected fun <Val> View.viewProp(
        defaultValue: Val
    ): ViewPropDelegate<Val> = ViewPropDelegate(mutableProps, defaultValue)

    abstract class ContentView<Self : ContentView<Self>>(
        viewId: ViewId? = null,
        templateName: String? = null
    ) : AbstractView(viewId, templateName), ManagedContent<View, Self> {

        private var content: Supplier<View?> = Supplier { null }

        final override fun setContent(content: View?): Self = setContent { content }

        final override fun getContent(): ViewContent = ViewContent.Template.of(content.get())

        protected abstract val self: Self
    }

}
