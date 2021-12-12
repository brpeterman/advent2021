package com.github.brpeterman.advent2021

import org.junit.jupiter.api.Assertions.*
import java.io.File
import kotlin.test.Test

internal class Day12Test {
    @Test
    fun example1a() {
        val input = """
            start-A
            start-b
            A-c
            A-b
            b-d
            A-end
            b-end
        """.trimIndent()
        val cave = Day12.parseInput(input)
        val solver = Day12()
        val paths = solver.countPaths(cave)

        assertEquals(10, paths)
    }

    @Test
    fun example1b() {
        val input = """
            dc-end
            HN-start
            start-kj
            dc-start
            dc-HN
            LN-dc
            HN-end
            kj-sa
            kj-HN
            kj-dc
        """.trimIndent()
        val cave = Day12.parseInput(input)
        val solver = Day12()
        val paths = solver.countPaths(cave)

        assertEquals(19, paths)
    }

    @Test
    fun example1c() {
        val input = """
            fs-end
            he-DX
            fs-he
            start-DX
            pj-DX
            end-zg
            zg-sl
            zg-pj
            pj-he
            RW-he
            fs-DX
            pj-RW
            zg-RW
            start-pj
            he-WI
            zg-he
            pj-fs
            start-RW
        """.trimIndent()
        val cave = Day12.parseInput(input)
        val solver = Day12()
        val paths = solver.countPaths(cave)

        assertEquals(226, paths)
    }

    @Test
    fun example2a() {
        val input = """
            start-A
            start-b
            A-c
            A-b
            b-d
            A-end
            b-end
        """.trimIndent()
        val cave = Day12.parseInput(input)
        val solver = Day12()
        val paths = solver.countPaths(cave, 1)

        assertEquals(36, paths)
    }

    @Test
    fun example2b() {
        val input = """
            dc-end
            HN-start
            start-kj
            dc-start
            dc-HN
            LN-dc
            HN-end
            kj-sa
            kj-HN
            kj-dc
        """.trimIndent()
        val cave = Day12.parseInput(input)
        val solver = Day12()
        val paths = solver.countPaths(cave, 1)

        assertEquals(103, paths)
    }

    @Test
    fun example2c() {
        val input = """
            fs-end
            he-DX
            fs-he
            start-DX
            pj-DX
            end-zg
            zg-sl
            zg-pj
            pj-he
            RW-he
            fs-DX
            pj-RW
            zg-RW
            start-pj
            he-WI
            zg-he
            pj-fs
            start-RW
        """.trimIndent()
        val cave = Day12.parseInput(input)
        val solver = Day12()
        val paths = solver.countPaths(cave, 1)

        assertEquals(3509, paths)
    }

    @Test
    fun solve1() {
        val input = File("inputs/day12.txt").readText()
        val cave = Day12.parseInput(input)
        val solver = Day12()
        val paths = solver.countPaths(cave)

        println("Total paths: $paths")
    }

    @Test
    fun solve2() {
        val input = File("inputs/day12.txt").readText()
        val cave = Day12.parseInput(input)
        val solver = Day12()
        val paths = solver.countPaths(cave, 1)

        println("Total paths: $paths")
    }
}
