package me.aliyuolalekan.common.data

class Queue<T> {

    private val container = arrayListOf<T>()

    val isEmpty: Boolean
        get() = container.isEmpty()

    val size: Int
        get() = container.size

    fun enqueue(item: T): Queue<T> {
        container.add(0, item)
        return this
    }

    fun asList(): List<T> = container

    fun <R> map(transform: (T) -> R) {
        for (item in container) {
            transform(item)
        }
    }

    fun peek(): T? = container[size - 1]

    fun dequeue(): T? {
        return container.removeAt(size - 1)
    }

    fun next(): T? {
        val current = dequeue()
        val next = peek()
        enqueue(current!!)
        return next
    }

    fun any(predicate: (T) -> Boolean): T? = container.firstOrNull(predicate)

    fun nextTo(item: T?): T? {
        item ?: return null
        if (!container.contains(item)) { return null }
        if (container.size == 1) {
            return container[0]
        }
        if (container.size == 2 && container[0] == item) {
            return container[1]
        }

        if (container[0] == item) {
            return container[container.size - 1]
        }

        var next: T? = null
        var found = false
        for (index in container.size - 1 downTo 0) {
            if (found) {
                next = container[index]
            }
            found = item == container[index]
        }

        return next
    }
}

fun <T> queueOf(vararg items: T): Queue<T> {
    val queue = Queue<T>()
    items.forEach { queue.enqueue(it) }
    return queue
}
