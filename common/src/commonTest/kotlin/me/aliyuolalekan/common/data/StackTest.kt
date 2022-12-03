package me.aliyuolalekan.common.data

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue

import org.junit.jupiter.api.Test

internal class StackTest {

    private val stack = Stack<String>()

    @Test
    fun `test push-pop-peek`() {
        stack.push("first")
        stack.push("second")
        assertTrue(stack.size == 2)
        assertEquals("second", stack.peek())
        assertTrue(stack.pop() == "second")
        assertFalse(stack.isEmpty)
        stack.pop()
        assertTrue(stack.isEmpty)
    }

    @Test
    fun `test asList`() {
        stack.push("first")
        stack.push("second")
        assertTrue(stack.asList().containsOnly("second", "first"))
    }

    @Test
    fun `test peek`() {
        stack.push("first")
        stack.push("second")
        assertEquals("second", stack.peek())
    }
}