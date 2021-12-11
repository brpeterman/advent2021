package com.github.brpeterman.advent2021

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class Day11 {
    fun simulate(state: MutableMap<Coords, Int>, steps: Int, print: Boolean = false): Int {
        if (print) {
            printState(state, 0)
        }

        var flashes = 0
        for (step in 1..steps) {
            flashes += step(state)

            if (print) {
                printState(state, step)
            }
        }

        return flashes
    }

    fun findSync(state: MutableMap<Coords, Int>): Int {
        val target = state.size
        var flashes = 0
        var step = 0
        while (flashes != target) {
            flashes = step(state)
            step += 1
        }
        return step
    }

    fun step(state: MutableMap<Coords, Int>): Int {
        val flashed = HashSet<Coords>()
        state.entries.forEach { octopus ->
            state[octopus.key] = octopus.value + 1
            if (state[octopus.key]!! > 9) {
                flashed.add(octopus.key)
            }
        }

        val toCheck = LinkedList<Coords>()
        flashed.forEach { position ->
            val neighbors = neighbors(state, flashed, position)
            toCheck.addAll(neighbors)
        }

        while (toCheck.isNotEmpty()) {
            val nextPosition = toCheck.pop()
            if (!flashed.contains(nextPosition)) {
                state[nextPosition] = state[nextPosition]!! + 1
            }
            if (state[nextPosition]!! > 9 && !flashed.contains(nextPosition)) {
                flashed.add(nextPosition)
                val neighbors = neighbors(state, flashed, nextPosition)
                toCheck.addAll(neighbors)
            }
        }

        flashed.forEach { position ->
            state[position] = 0
        }

        return flashed.size
    }

    fun printState(state: Map<Coords, Int>, step: Int) {
        println("Step $step")
        val maxX = state.keys.maxByOrNull { pos -> pos.x }!!.x
        val maxY = state.keys.maxByOrNull { pos -> pos.y }!!.y
        for (y in 0..maxY) {
            var line = ""
            for (x in 0..maxX) {
                line += state[Coords(x, y)]!!
            }
            println(line)
        }
        println()
    }

    fun neighbors(state: Map<Coords, Int>, flashed: Set<Coords>, position: Coords): Set<Coords> {
        val neighbors = HashSet<Coords>()
        listOf(
            Pair(-1, -1),
            Pair(0, -1),
            Pair(1, -1),
            Pair(-1, 0),
            Pair(1, 0),
            Pair(-1, 1),
            Pair(0, 1),
            Pair(1, 1))
            .forEach { offset ->
                val neighborPosition = position + Coords(offset.first, offset.second)
                if (state[neighborPosition] != null && !flashed.contains(neighborPosition)) {
                    neighbors.add(neighborPosition)
                }
            }
        return neighbors
    }

    companion object {
        fun parseInput(input: String): MutableMap<Coords, Int> {
            val sea = HashMap<Coords, Int>()
            input.split("\n")
                .filter { it.isNotEmpty() }
                .forEachIndexed { y, line ->
                    line.split("")
                        .filter { it.isNotEmpty() }
                        .forEachIndexed { x, level ->
                            sea[Coords(x, y)] = level.toInt()
                        }
                }
            return sea
        }
    }

    data class Coords(val x: Int, val y: Int) {
        operator fun plus(other: Coords): Coords {
            return Coords(x + other.x, y + other.y)
        }
    }
}
