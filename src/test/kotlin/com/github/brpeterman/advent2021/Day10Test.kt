package com.github.brpeterman.advent2021

import org.junit.jupiter.api.Assertions.*
import java.io.File
import kotlin.test.Test

internal class Day10Test {
    @Test
    fun example1() {
        val input = """
            [({(<(())[]>[[{[]{<()<>>
            [(()[<>])]({[<{<<[]>>(
            {([(<{}[<>[]}>{[]{[(<()>
            (((({<>}<{<{<>}{[]{[]{}
            [[<[([]))<([[{}[[()]]]
            [{[{({}]{}}([{[{{{}}([]
            {<[[]]>}<{[{[{[]{()[[[]
            [<(<(<(<{}))><([]([]()
            <{([([[(<>()){}]>(<<{{
            <{([{{}}[<[[[<>{}]]]>[]]
        """.trimIndent()
        val lines = Day10.parseInput(input)
        val solver = Day10()
        val score = solver.scoreErrors(lines)

        assertEquals(26397, score)
    }

    @Test
    fun example2() {
        val input = """
            [({(<(())[]>[[{[]{<()<>>
            [(()[<>])]({[<{<<[]>>(
            {([(<{}[<>[]}>{[]{[(<()>
            (((({<>}<{<{<>}{[]{[]{}
            [[<[([]))<([[{}[[()]]]
            [{[{({}]{}}([{[{{{}}([]
            {<[[]]>}<{[{[{[]{()[[[]
            [<(<(<(<{}))><([]([]()
            <{([([[(<>()){}]>(<<{{
            <{([{{}}[<[[[<>{}]]]>[]]
        """.trimIndent()
        val lines = Day10.parseInput(input)
        val solver = Day10()
        val score = solver.scoreCompletions(lines)

        assertEquals(288957, score)
    }

    @Test
    fun solve1() {
        val input = File("inputs/day10.txt").readText()
        val lines = Day10.parseInput(input)
        val solver = Day10()
        val score = solver.scoreErrors(lines)

        println("Score: $score")
    }

    @Test
    fun solve2() {
        val input = File("inputs/day10.txt").readText()
        val lines = Day10.parseInput(input)
        val solver = Day10()
        val score = solver.scoreCompletions(lines)

        println("Score: $score")
    }
}
