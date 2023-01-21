package app.viewex.composer

interface ManagedContent<Content, Self : ManagedContent<Content, Self>> : Managed {
    fun setContent(content: Content?): Self
    fun cloneWithContent(content: Content?): Self
}
