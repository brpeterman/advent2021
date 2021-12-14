package com.github.brpeterman.advent2021

import org.junit.jupiter.api.Assertions.*
import java.io.File
import kotlin.test.Test

internal class Day14Test {
    @Test
    fun example1() {
        val input = """
            NNCB

            CH -> B
            HH -> N
            CB -> H
            NH -> C
            HB -> C
            HC -> B
            HN -> C
            NN -> C
            BH -> H
            NC -> B
            NB -> B
            BN -> B
            BB -> N
            BC -> B
            CC -> N
            CN -> C
        """.trimIndent()
        val formula = Day14.parseInput(input)
        val solver = Day14()
        val pairs = solver.synthesize(formula, 10)

        println("Frequencies: ${pairs.entries.joinToString("; ")}")

        val difference = mostCommon(pairs) - leastCommon(pairs)
        assertEquals(1588, difference)
    }

    @Test
    fun example2() {
        val input = """
            NNCB

            CH -> B
            HH -> N
            CB -> H
            NH -> C
            HB -> C
            HC -> B
            HN -> C
            NN -> C
            BH -> H
            NC -> B
            NB -> B
            BN -> B
            BB -> N
            BC -> B
            CC -> N
            CN -> C
        """.trimIndent()
        val formula = Day14.parseInput(input)
        val solver = Day14()
        val output = solver.synthesize(formula, 40)

        val difference = mostCommon(output) - leastCommon(output)
        assertEquals(2188189693529, difference)
    }

    @Test
    fun solve1() {
        val input = File("inputs/day14.txt").readText()
        val formula = Day14.parseInput(input)
        val solver = Day14()
        val output = solver.synthesize(formula, 10)

        val difference = mostCommon(output) - leastCommon(output)
        println("Difference: $difference")
    }

    @Test
    fun solve2() {
        val input = File("inputs/day14.txt").readText()
        val formula = Day14.parseInput(input)
        val solver = Day14()
        val output = solver.synthesize(formula, 40)

        val difference = mostCommon(output) - leastCommon(output)
        println("Difference: $difference")
    }

    fun leastCommon(pairs: Map<String, Long>): Long {
        return pairs.minOfOrNull { entry -> entry.value }!!
    }

    fun mostCommon(pairs: Map<String, Long>): Long {
        return pairs.maxOfOrNull { entry -> entry.value }!!
    }
}
