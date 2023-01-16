package app.viewex.core.details

abstract class ObjectGroup(items: Iterable<Item>) : IdentifiedPath(items) {
    object Empty : ObjectGroup(emptyList())
}
