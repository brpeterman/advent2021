package com.github.brpeterman.advent2021

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class Day9 {
    fun riskScore(map: Map<Coords, Int>): Int {
        return lowPoints(map)
            .fold(0, { riskSum, coord -> riskSum + map.getValue(coord) + 1})
    }

    fun lowPoints(map: Map<Coords, Int>): List<Coords> {
        return map.entries
            .filter { entry ->
                adjacents(map, entry.key).all { it.second > entry.value }
            }
            .map { it.key }
    }

    fun adjacents(map: Map<Coords, Int>, point: Coords): List<Pair<Coords, Int>> {
        val up = point + Coords(0, -1)
        val down = point + Coords(0, 1)
        val left = point + Coords(-1, 0)
        val right = point + Coords(1, 0)
        return listOf(
            Pair(up, map.getValue(up)),
            Pair(down, map.getValue(down)),
            Pair(left, map.getValue(left)),
            Pair(right, map.getValue(right))
        )
    }

    fun findBasinScores(map: Map<Coords, Int>): List<Int> {
        val lowPoints = lowPoints(map)
        val basins = lowPoints.map { start ->
            createBasin(map, start)
        }
        // Since we flood-filled, overlapping basins will be identical
        return basins.distinct()
            .map { it.size }
    }

    fun createBasin(map: Map<Coords, Int>, start: Coords): Set<Coords> {
        // Flood fill, treating 9 as a barrier
        val basin = HashSet<Coords>()
        val checkQueue = LinkedList(adjacents(map, start)
            .filter { it.second < 9 }
            .map { it.first })
        while (checkQueue.isNotEmpty()) {
            val nextPoint = checkQueue.pop()
            if (map.getValue(nextPoint) < 9) {
                basin.add(nextPoint)
                val newNeighbors = adjacents(map, nextPoint)
                    .filter { it.second < 9 }
                    .map { it.first }
                    .filter { !basin.contains(it) }
                checkQueue.addAll(newNeighbors)
            }
        }
        return basin
    }

    companion object {
        fun parseInput(input: String): Map<Coords, Int> {
            val map = HashMap<Coords, Int>().withDefault { 9 }
            input.split("\n")
                .filter { it.isNotEmpty() }
                .forEachIndexed{ y, line ->
                    line.split("")
                        .filter { it.isNotEmpty() }
                        .forEachIndexed { x, spot ->
                            map[Coords(x, y)] = spot.toInt()
                        }
                }
            return map
        }
    }

    data class Coords(val x: Int, val y: Int) {
        operator fun plus(other: Coords): Coords {
            return Coords(x + other.x, y + other.y)
        }
    }
}
