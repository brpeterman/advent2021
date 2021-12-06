package com.github.brpeterman.advent2021

import org.junit.jupiter.api.Assertions.*
import java.io.File
import kotlin.test.Test

internal class Day6Test {
    @Test
    fun example1() {
        val input = """
            3,4,3,1,2
        """.trimIndent()
        val initialFish = Day6.parseInput(input)
        val solver = Day6()
        val finalFish = solver.simulateGrowth(initialFish, 80)

        assertEquals(5934, finalFish)
    }

    @Test
    fun example2() {
        val input = """
            3,4,3,1,2
        """.trimIndent()
        val initialFish = Day6.parseInput(input)
        val solver = Day6()
        val finalFish = solver.simulateGrowth(initialFish, 256)

        assertEquals(26984457539, finalFish)
    }

    @Test
    fun solve1() {
        val input = File("inputs/day6.txt").readText()
        val initialFish = Day6.parseInput(input)
        val solver = Day6()
        val finalFish = solver.simulateGrowth(initialFish, 80)

        println("Fish after 80 days: $finalFish")
    }

    @Test
    fun solve2() {
        val input = File("inputs/day6.txt").readText()
        val initialFish = Day6.parseInput(input)
        val solver = Day6()
        val finalFish = solver.simulateGrowth(initialFish, 256)

        println("Fish after 256 days: $finalFish")
    }
}
