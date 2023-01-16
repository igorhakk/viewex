package app.viewex.composer.action

import app.viewex.composer.IdentifiedView
import app.viewex.composer.ViewId
import app.viewex.core.details.Named
import app.viewex.core.util.ClassUtils

open class ViewAction<Data>(
    override val viewId: ViewId,
    override val name: ActionName,
    val data: Data?
) : Named<ActionName>, IdentifiedView {
    override fun toString(): String = ClassUtils.memberPropsToString(this)
}
