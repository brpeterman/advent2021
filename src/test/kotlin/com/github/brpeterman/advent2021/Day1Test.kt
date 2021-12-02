package com.github.brpeterman.advent2021

import org.junit.jupiter.api.Assertions.*
import java.io.File
import kotlin.test.Test

internal class Day1Test {
    @Test
    fun example1() {
        val input = Day1.parseInput("""
            199
            200
            208
            210
            200
            207
            240
            269
            260
            263""".trimIndent())
        val solver = Day1()
        val count = solver.measureIncreases(input)

        assertEquals(7, count)
    }

    @Test
    fun example2() {
        val input = Day1.parseInput("""
            199
            200
            208
            210
            200
            207
            240
            269
            260
            263""".trimIndent())
        val solver = Day1()
        val count = solver.measureIncreases(input, 3)

        assertEquals(5, count)
    }

    @Test
    fun problem1() {
        val input = Day1.parseInput(File("inputs/day1.txt").readText())
        val solver = Day1()
        val count = solver.measureIncreases(input)

        println("Increases: $count")
    }

    @Test
    fun problem2() {
        val input = Day1.parseInput(File("inputs/day1.txt").readText())
        val solver = Day1()
        val count = solver.measureIncreases(input, 3)

        println("Increases: $count")
    }
}
