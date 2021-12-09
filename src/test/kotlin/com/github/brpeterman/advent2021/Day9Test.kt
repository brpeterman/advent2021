package com.github.brpeterman.advent2021

import org.junit.jupiter.api.Assertions.*
import java.io.File
import kotlin.test.Test

internal class Day9Test {
    @Test
    fun example1() {
        val input = """
            2199943210
            3987894921
            9856789892
            8767896789
            9899965678
        """.trimIndent()
        val map = Day9.parseInput(input)
        val solver = Day9()
        val risk = solver.riskScore(map)

        assertEquals(15, risk)
    }

    @Test
    fun example2() {
        val input = """
            2199943210
            3987894921
            9856789892
            8767896789
            9899965678
        """.trimIndent()
        val map = Day9.parseInput(input)
        val solver = Day9()
        val scores = solver.findBasinScores(map)
        val topThree = scores.sorted()
            .reversed()
            .subList(0, 3)
            .fold(1, { product, size -> product * size })

        assertEquals(1134, topThree)
    }

    @Test
    fun solve1() {
        val input = File("inputs/day9.txt").readText()
        val map = Day9.parseInput(input)
        val solver = Day9()
        val risk = solver.riskScore(map)

        println("Risk score: $risk")
    }

    @Test
    fun solve2() {
        val input = File("inputs/day9.txt").readText()
        val map = Day9.parseInput(input)
        val solver = Day9()
        val scores = solver.findBasinScores(map)
        val topThree = scores.sorted()
            .reversed()
            .subList(0, 3)
            .fold(1, { product, size -> product * size })

        println("Largest basin scores: $topThree")
    }
}
