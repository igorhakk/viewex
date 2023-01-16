package app.viewex.composer.view

import app.viewex.composer.*
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.javaField

abstract class AbstractView<Content : ViewContent>(
    private val context: ViewContext,
    templateName: String? = null
) : View, ManagedView(context) {

    private val _templateName: TemplateName = templateName?.let {
        TemplateName(it)
    } ?: TemplateName.ofDefinitionClass(this::class)

    private var content = ViewContent.Empty

    protected fun setContent(content: Content) = updateContent(content).also { this.content = content }

    final override fun getTemplate(): ViewTemplate = ViewTemplate(
        viewId,
        _templateName,
        composeProps(),
        content
    )

    private fun composeProps(): ViewPropSet = this::class.declaredMemberProperties.filter {
        it.javaField?.type?.isAssignableFrom(PropDelegate::class.java) == true
    }.mapNotNull {
        val value = it.call(this) ?: return@mapNotNull null
        ViewPropSet.Prop(it.name, value)
    }.let { ViewPropSet(it) }

    final override fun toString(): String = this::class.simpleName + "[ " + getTemplate().toString() + " ]"

    protected fun <Val> AbstractView<*>.viewProp(
        defaultValue: Val
    ): PropDelegate<Val> = PropDelegate(this.context, viewId, defaultValue)

}
