package app.viewex.core.attribute

class PairList : IterablePair<String, Any?> {

    private val list: MutableList<Pair<String, Any?>> = mutableListOf()

    fun add(name: String, value: Any) {
        list.add(Pair(name, value))
    }

    override fun iterator(): Iterator<Pair<String, Any?>> = list.iterator()
}
