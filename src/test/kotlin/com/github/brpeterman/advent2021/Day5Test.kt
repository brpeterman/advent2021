package com.github.brpeterman.advent2021

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day5Test {
    @Test
    fun example1() {
        val input = """
            0,9 -> 5,9
            8,0 -> 0,8
            9,4 -> 3,4
            2,2 -> 2,1
            7,0 -> 7,4
            6,4 -> 2,0
            0,9 -> 2,9
            3,4 -> 1,4
            0,0 -> 8,8
            5,5 -> 8,2
        """.trimIndent()
        val segments = Day5.parseInput(input)
        val solver = Day5()
        val intersections = solver.findIntersections(segments)

        assertEquals(5, intersections.size)
    }

    @Test
    fun example2() {
        val input = """
            0,9 -> 5,9
            8,0 -> 0,8
            9,4 -> 3,4
            2,2 -> 2,1
            7,0 -> 7,4
            6,4 -> 2,0
            0,9 -> 2,9
            3,4 -> 1,4
            0,0 -> 8,8
            5,5 -> 8,2
        """.trimIndent()
        val segments = Day5.parseInput(input)
        val solver = Day5()
        val intersections = solver.findIntersections(segments, true)

        assertEquals(12, intersections.size)
    }

    @Test
    fun solve1() {
        val input = File("inputs/day5.txt").readText()
        val segments = Day5.parseInput(input)
        val solver = Day5()
        val intersections = solver.findIntersections(segments)

        println("Intersections: ${intersections.size}")
    }

    @Test
    fun solve2() {
        val input = File("inputs/day5.txt").readText()
        val segments = Day5.parseInput(input)
        val solver = Day5()
        val intersections = solver.findIntersections(segments, true)

        println("Intersections: ${intersections.size}")
    }
}
