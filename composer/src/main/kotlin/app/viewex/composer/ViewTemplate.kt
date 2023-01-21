package app.viewex.composer

import app.viewex.core.util.ClassUtils

open class ViewTemplate(
    final override val viewId: ViewId,
    val template: TemplateName,
    val props: ViewProps = ViewProps.Empty,
    val content: ViewContent = ViewContent.Empty
) : ViewContent, IdentifiedView {

    override fun toString(): String = ClassUtils.memberPropsToString(this)


}
