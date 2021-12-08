package com.github.brpeterman.advent2021

import org.junit.jupiter.api.Assertions.*
import java.io.File
import kotlin.test.Test

internal class Day7Test {
    @Test
    fun example1() {
        val input = """
            16,1,2,0,4,2,7,1,2,14
        """.trimIndent()
        val positions = Day7.parseInput(input)
        val solver = Day7()
        val fuel = solver.align(positions)

        assertEquals(37, fuel)
    }

    @Test
    fun example2() {
        val input = """
            16,1,2,0,4,2,7,1,2,14
        """.trimIndent()
        val positions = Day7.parseInput(input)
        val solver = Day7()
        val fuel = solver.align(positions, true)

        assertEquals(168, fuel)
    }

    @Test
    fun solve1() {
        val input = File("inputs/day7.txt").readText()
        val positions = Day7.parseInput(input)
        val solver = Day7()
        val fuel = solver.align(positions)

        println("Fuel used: $fuel")
    }

    @Test
    fun solve2() {
        val input = File("inputs/day7.txt").readText()
        val positions = Day7.parseInput(input)
        val solver = Day7()
        val fuel = solver.align(positions, true)

        println("Fuel used: $fuel")
    }
}
