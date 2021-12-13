package com.github.brpeterman.advent2021

import org.junit.jupiter.api.Assertions.*
import java.io.File
import kotlin.test.Test

internal class Day13Test {
    @Test
    fun example1() {
        val input = """
            6,10
            0,14
            9,10
            0,3
            10,4
            4,11
            6,0
            6,12
            4,1
            0,13
            10,12
            3,4
            3,0
            8,4
            1,10
            2,14
            8,10
            9,0

            fold along y=7
            fold along x=5
        """.trimIndent()
        val manual = Day13.parseInput(input)
        val solver = Day13()
        val folded = solver.fold(manual)

        println(folded.toString())

        assertEquals(17, folded.dots.size)
    }

    @Test
    fun example2() {
        val input = """
            6,10
            0,14
            9,10
            0,3
            10,4
            4,11
            6,0
            6,12
            4,1
            0,13
            10,12
            3,4
            3,0
            8,4
            1,10
            2,14
            8,10
            9,0

            fold along y=7
            fold along x=5
        """.trimIndent()
        val manual = Day13.parseInput(input)
        val solver = Day13()
        val folded = solver.foldAll(manual)

        println(folded.toString())
    }

    @Test
    fun solve1() {
        val input = File("inputs/day13.txt").readText()
        val manual = Day13.parseInput(input)
        val solver = Day13()
        val folded = solver.fold(manual)

        println("Dots after 1 fold: ${folded.dots.size}")
    }

    @Test
    fun solve2() {
        val input = File("inputs/day13.txt").readText()
        val manual = Day13.parseInput(input)
        val solver = Day13()
        val folded = solver.foldAll(manual)

        println(folded.toString())
    }
}
