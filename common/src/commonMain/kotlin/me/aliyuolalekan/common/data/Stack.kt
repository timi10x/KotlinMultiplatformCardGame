package me.aliyuolalekan.common.data

class Stack<T> {

    val isEmpty: Boolean
        get() = container.isEmpty()

    val size: Int
        get() = container.size

    private val container = arrayListOf<T>()

    fun push(item: T): Stack<T> {
        container.add(item)
        return this
    }

    fun pop(): T? {
        if (isEmpty) return null
        return container.removeAt(size - 1)
    }

    fun shuffle(): Stack<T> {
        container.shuffle()
        return this
    }

    fun asList(): List<T> = container

    fun peek(): T? {
        if (isEmpty) return null
        return container[size - 1]
    }

    /**
     * This violates stack behaviour, however it's needed for the
     * market cards to work properly.
     */
    fun addFromBack(card: T) {
        container.add(0, card)
    }
}

fun<T> stackOf(vararg items: T): Stack<T> {
    val stack = Stack<T>()
    for (item in items) {
        stack.push(item)
    }
    return stack
}