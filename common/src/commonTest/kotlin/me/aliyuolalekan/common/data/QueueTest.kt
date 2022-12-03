package me.aliyuolalekan.common.data

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class QueueTest {

    private val queue = Queue<String>()

    @Test
    fun `test enqueue-dequeue`() {
        queue.enqueue("first")
        assertTrue(queue.size == 1)
        assertFalse(queue.isEmpty)
        assertEquals(queue.dequeue(), "first")
        assertTrue(queue.size == 0)
        assertTrue(queue.isEmpty)
    }

    @Test
    fun `test isEmpty`() {
        assertTrue(queue.isEmpty)
    }

    @Test
    fun `test asList`() {
        assertTrue(
            queue.enqueue("first")
                .enqueue("second")
                .asList()
                .containsOnly("first", "second")
        )
    }

    @Test
    fun `test map`() {
        queue.enqueue("first")
            .enqueue("second")
            .map { it.length }
        queue.asList().containsOnly("5", "6")
    }

    @Test
    fun `test any`() {
        assertTrue(
            queue.enqueue("first")
                .enqueue("second")
                .any { it == "first" } == "first"
        )
    }

    @Test
    fun `test next`() {
        assertEquals(queue.enqueue("first").enqueue("second").enqueue("third").next(), "second")
    }

    @Test
    fun `test next after`() {
        assertEquals(
            "third",
            queue.enqueue("first")
                .enqueue("second")
                .enqueue("third")
                .nextTo("second")
        )

        assertEquals(
            "first",
            queue.enqueue("first")
                .enqueue("second")
                .enqueue("third")
                .nextTo("third")
        )

        assertEquals(
            "first",
            queue.enqueue("first")
                .enqueue("second")
                .nextTo("second")
        )

        assertEquals(
            "second",
            queue.enqueue("first")
                .enqueue("second")
                .nextTo("first")
        )

        assertEquals(
            "first",
            queue.enqueue("first")
                .enqueue("second")
                .nextTo("second")
        )
    }
}

fun <T> List<T>.containsOnly(vararg items: T): Boolean {
    if (size != items.size) return false
    items.forEach { item -> if (!contains(item)) return false }
    return true
}