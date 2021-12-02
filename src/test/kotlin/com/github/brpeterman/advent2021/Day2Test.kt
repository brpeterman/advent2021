package com.github.brpeterman.advent2021

import org.junit.jupiter.api.Assertions.*
import java.io.File
import kotlin.test.Test

internal class Day2Test {
    @Test
    fun example1() {
        val input = """
            forward 5
            down 5
            forward 8
            up 3
            down 8
            forward 2
        """.trimIndent()
        val commands = Day2.parseInput(input)
        val solver = Day2()
        val position = solver.pilot(commands)

        assertEquals(150, position.range * position.depth)
    }

    @Test
    fun example2() {
        val input = """
            forward 5
            down 5
            forward 8
            up 3
            down 8
            forward 2
        """.trimIndent()
        val commands = Day2.parseInput(input)
        val solver = Day2()
        val position = solver.pilotWithAim(commands)

        assertEquals(900, position.range * position.depth)
    }

    @Test
    fun solve1() {
        val input = File("inputs/day2.txt").readText()
        val commands = Day2.parseInput(input)
        val solver = Day2()
        val position = solver.pilot(commands)

        println("Range x Depth: ${position.range * position.depth}")
    }

    @Test
    fun solve2() {
        val input = File("inputs/day2.txt").readText()
        val commands = Day2.parseInput(input)
        val solver = Day2()
        val position = solver.pilotWithAim(commands)

        println("Range x Depth: ${position.range * position.depth}")
    }
}
