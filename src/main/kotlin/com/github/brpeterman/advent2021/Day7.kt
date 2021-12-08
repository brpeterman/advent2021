package com.github.brpeterman.advent2021

class Day7 {
    fun align(positions: List<Int>, inertia: Boolean = false): Int {
        return (0..positions.maxOrNull()!!).map { p -> alignTo(positions, p, inertia) }
            .minOrNull()!!
    }

    fun alignTo(positions: List<Int>, goal: Int, inertia: Boolean): Int {
        return positions.fold(0, { sum, n ->
            val distance = Math.abs(goal - n)
            sum + if (inertia) {
                (distance * (distance + 1)) / 2
            } else {
                distance
            }
        })
    }

    companion object {
        fun parseInput(input: String): List<Int> {
            return input.split(",")
                .filter { n -> n.isNotEmpty() }
                .map { n -> n.trim().toInt() }
        }
    }
}
