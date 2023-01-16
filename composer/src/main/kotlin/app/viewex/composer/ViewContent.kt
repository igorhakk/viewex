package app.viewex.composer

interface ViewContent {

    companion object {
        val Empty: ViewContent = object : ViewContent {
            override fun toString(): String = "empty"
        }
    }
}
