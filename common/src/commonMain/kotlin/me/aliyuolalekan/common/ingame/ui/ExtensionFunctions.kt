package me.aliyuolalekan.common.ingame.ui

import me.aliyuolalekan.common.data.Stack
import me.aliyuolalekan.common.domain.Player

fun <T> List<T>.toStack(): Stack<T> {
    val stack = Stack<T>()
    forEach { stack.push(it) }
    return stack
}

fun getTestPlayers() =
    listOf(
        Player(1, "Phil", isSelf = true, photo = "player_one"),
        Player(2, "Daniel", photo = "player_two", isAI = true)
    )