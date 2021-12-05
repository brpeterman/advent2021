package com.github.brpeterman.advent2021

class Day5 {
    fun findIntersections(segments: List<Pair<Coords, Coords>>, includeDiagonals: Boolean = false): List<Coords> {
        val locations = HashMap<Coords, Int>().withDefault { 0 }
        segments.forEach { segment ->
            if (segment.first.x == segment.second.x) {
                val (start, end) = listOf(segment.first, segment.second).sortedBy { c -> c.y }
                val x = start.x
                for (y in start.y..end.y) {
                    locations[Coords(x, y)] = locations.getValue(Coords(x, y)) + 1
                }
            } else if (segment.first.y == segment.second.y) {
                val (start, end) = listOf(segment.first, segment.second).sortedBy { c -> c.x }
                val y = start.y
                for (x in start.x..end.x) {
                    locations[Coords(x, y)] = locations.getValue(Coords(x, y)) + 1
                }
            } else if (includeDiagonals) {
                val (start, end) = listOf(segment.first, segment.second).sortedBy { c -> c.x }
                val step = if (start.y < end.y) 1 else -1
                (start.x..end.x).forEachIndexed { offset, x ->
                    val y = start.y + (offset * step)
                    locations[Coords(x, y)] = locations.getValue(Coords(x, y)) + 1
                }
            }
        }

        return locations.entries
            .filter { entry -> entry.value > 1 }
            .map { entry -> entry.key }
    }

    companion object {
        fun parseInput(input: String): List<Pair<Coords, Coords>> {
            return input.split("\n")
                .filter { line -> line.isNotEmpty() }
                .map { line ->
                    val result = """\A(\d+),(\d+) -> (\d+),(\d+)\Z""".toRegex().matchEntire(line)!!
                    val (x1, y1, x2, y2) = result.groupValues.drop(1)
                    Pair(Coords(x1.toInt(), y1.toInt()), Coords(x2.toInt(), y2.toInt()))
                }
        }
    }

    data class Coords(val x: Int, val y: Int)
}
