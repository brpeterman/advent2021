package com.github.brpeterman.advent2021

import org.junit.jupiter.api.Assertions.*
import java.io.File
import kotlin.test.Test

internal class Day11Test {
    @Test
    fun example1a() {
        val input = """
            11111
            19991
            19191
            19991
            11111
        """.trimIndent()
        val state = Day11.parseInput(input)
        val solver = Day11()
        val flashes = solver.simulate(state, 2, true)

        assertEquals(9, flashes)
    }

    @Test
    fun example1b() {
        val input = """
            5483143223
            2745854711
            5264556173
            6141336146
            6357385478
            4167524645
            2176841721
            6882881134
            4846848554
            5283751526
        """.trimIndent()
        val state = Day11.parseInput(input)
        val solver = Day11()
        val flashes = solver.simulate(state, 10, true)

        assertEquals(204, flashes)

        val additionalFlashes = solver.simulate(state, 90)

        assertEquals(1656, flashes + additionalFlashes)
    }

    @Test
    fun example2() {
        val input = """
            5483143223
            2745854711
            5264556173
            6141336146
            6357385478
            4167524645
            2176841721
            6882881134
            4846848554
            5283751526
        """.trimIndent()
        val state = Day11.parseInput(input)
        val solver = Day11()
        val step = solver.findSync(state)

        assertEquals(195, step)
    }

    @Test
    fun solve1() {
        val input = File("inputs/day11.txt").readText()
        val state = Day11.parseInput(input)
        val solver = Day11()
        val flashes = solver.simulate(state, 100)

        println("Flashes: $flashes")
    }

    @Test
    fun solve2() {
        val input = File("inputs/day11.txt").readText()
        val state = Day11.parseInput(input)
        val solver = Day11()
        val step = solver.findSync(state)

        println("Sync step: $step")
    }
}
