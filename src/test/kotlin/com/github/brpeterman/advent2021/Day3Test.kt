package com.github.brpeterman.advent2021

import org.junit.jupiter.api.Assertions.*
import java.io.File
import kotlin.test.Test

internal class Day3Test {
    @Test
    fun example1() {
        val input = """
            00100
            11110
            10110
            10111
            10101
            01111
            00111
            11100
            10000
            11001
            00010
            01010
        """.trimIndent()
        val codes = Day3.parseInput(input)
        val solver = Day3()
        val power = solver.powerConsumption(codes)

        assertEquals(198, power)
    }

    @Test
    fun example2() {
        val input = """
            00100
            11110
            10110
            10111
            10101
            01111
            00111
            11100
            10000
            11001
            00010
            01010
        """.trimIndent()
        val codes = Day3.parseInput(input)
        val solver = Day3()
        val lifeSupport = solver.lifeSupportRating(codes)

        assertEquals(230, lifeSupport)
    }

    @Test
    fun solve1() {
        val input = File("inputs/day3.txt").readText()
        val codes = Day3.parseInput(input)
        val solver = Day3()
        val power = solver.powerConsumption(codes)

        println("Power consumption: $power")
    }

    @Test
    fun solve2() {
        val input = File("inputs/day3.txt").readText()
        val codes = Day3.parseInput(input)
        val solver = Day3()
        val lifeSupport = solver.lifeSupportRating(codes)

        println("Life support rating: $lifeSupport")
    }
}
