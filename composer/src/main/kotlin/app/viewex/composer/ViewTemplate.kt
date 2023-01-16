package app.viewex.composer

import app.viewex.core.util.ClassUtils

class ViewTemplate(
    override val viewId: ViewId,
    val template: TemplateName,
    val props: ViewPropSet = ViewPropSet.Empty,
    val content: ViewContent = ViewContent.Empty
) : IdentifiedView, ViewContent {
    override fun toString(): String = ClassUtils.memberPropsToString(this)
}
