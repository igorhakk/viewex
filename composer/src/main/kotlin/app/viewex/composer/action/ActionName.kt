package app.viewex.composer.action

import app.viewex.core.details.ObjectName
import app.viewex.core.type.StringType

class ActionName(
    name: String
) : StringType(name, 3, 50, Pattern), ObjectName {

    companion object {
        const val Pattern = "[a-z][a-zA-Z]*"
    }

}
