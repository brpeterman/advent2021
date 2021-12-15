package com.github.brpeterman.advent2021

import org.junit.jupiter.api.Assertions.*
import java.io.File
import kotlin.test.Test

internal class Day15Test {
    @Test
    fun example1() {
        val input = """
            1163751742
            1381373672
            2136511328
            3694931569
            7463417111
            1319128137
            1359912421
            3125421639
            1293138521
            2311944581
        """.trimIndent()
        val map = Day15.parseInput(input)
        val solver = Day15()
        val pathCost = solver.findSafestPath(map)

        assertEquals(40, pathCost)
    }

    @Test
    fun example2() {
        val input = """
            1163751742
            1381373672
            2136511328
            3694931569
            7463417111
            1319128137
            1359912421
            3125421639
            1293138521
            2311944581
        """.trimIndent()
        val map = Day15.parseInput(input)
        val solver = Day15()
        val pathCost = solver.findSafestPath(map, 5)

        assertEquals(315, pathCost)
    }

    @Test
    fun solve1() {
        val input = File("inputs/day15.txt").readText()
        val map = Day15.parseInput(input)
        val solver = Day15()
        val pathCost = solver.findSafestPath(map)

        println("Path cost: $pathCost")
    }

    @Test
    fun solve2() {
        val input = File("inputs/day15.txt").readText()
        val map = Day15.parseInput(input)
        val solver = Day15()
        val pathCost = solver.findSafestPath(map, 5)

        println("Path cost: $pathCost")
    }
}
