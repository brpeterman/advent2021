package com.github.brpeterman.advent2021

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class Day15 {
    fun findSafestPath(cave: CaveMap, size: Int = 1): Int {
        val maxX = cave.width * size - 1
        val maxY = cave.height * size - 1
        val end = Coords(maxX, maxY)
        val visited = HashSet<Coords>()
        val unvisited = PriorityQueue<QueueEntry>()
        val distances = HashMap<Coords, Int>().withDefault { Integer.MAX_VALUE }
        val parents = HashMap<Coords, Coords>()
        var current = Coords(0, 0)
        distances[current] = 0
        while (!visited.contains(end)) {
            newNeighbors(current, cave, size, visited).forEach { node ->
                val weight = cave.getRisk(node)
                val newDistance = distances.getValue(current) + weight
                if (newDistance < distances.getValue(node)) {
                    unvisited.remove(QueueEntry(node))
                    unvisited.add(QueueEntry(node, newDistance))
                    distances[node] = newDistance
                    parents[node] = current
                }
            }
            unvisited.remove(QueueEntry(current))
            visited.add(current)
            if (unvisited.isNotEmpty()) {
                current = unvisited.first().position
            }
        }
        return distances.getValue(end)
    }

    fun newNeighbors(node: Coords, cave: CaveMap, size: Int, visited: Set<Coords>): List<Coords> {
        return listOf(
            Coords(-1, 0),
            Coords(1, 0),
            Coords(0, -1),
            Coords(0, 1))
            .map { offset -> node + offset }
            .filter { cave.contains(it, size) }
            .filter { !visited.contains(it) }
    }

    companion object {
        fun parseInput(input: String): CaveMap {
            val map = HashMap<Coords, Int>()
            input.split("\n")
                .filter { it.isNotEmpty() }
                .forEachIndexed { y, line ->
                    line.split("")
                        .filter { it.isNotEmpty() }
                        .forEachIndexed { x, weight ->
                            map[Coords(x, y)] = weight.toInt()
                        }
                }
            return CaveMap(map)
        }
    }

    class CaveMap(val riskScores: Map<Coords, Int>) {
        val width: Int
        val height: Int
        init {
            width = riskScores.keys.maxOfOrNull { it.x }!! + 1
            height = riskScores.keys.maxOfOrNull { it.y }!! + 1
        }

        fun contains(position: Coords, size: Int): Boolean {
            return (position.x < (size * width)) &&
                    (position.x >= 0) &&
                    (position.y < (size * height)) &&
                    (position.y >= 0)
        }

        fun getRisk(position: Coords): Int {
            val tileX = position.x / width
            val tileY = position.y / height
            val originalRisk = riskScores[Coords(position.x % width, position.y % height)]!!
            val adjustment = tileX + tileY
            return (((originalRisk - 1) + adjustment) % 9) + 1 // Everything is mod 9 + 1
        }
    }

    data class Coords(val x: Int, val y: Int) {
        operator fun plus(other: Coords): Coords {
            return Coords(x + other.x, y + other.y)
        }
    }

    class QueueEntry(val position: Coords, val distance: Int = 0): Comparable<QueueEntry> {
        override fun compareTo(other: QueueEntry): Int {
            return distance.compareTo(other.distance)
        }

        override fun equals(other: Any?): Boolean {
            if (other is QueueEntry) {
                return position.equals(other.position)
            }
            return false
        }

        override fun hashCode(): Int {
            return position.hashCode()
        }
    }
}
