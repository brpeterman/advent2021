package com.github.brpeterman.advent2021

class Day2 {
    fun pilot(commands: List<SubCommand>): Coords {
        return commands.fold(Coords(0, 0), { current, command ->
            if (command.direction == Direction.UP) {
                current + Coords(0, -1 * command.magnitude)
            } else if (command.direction == Direction.DOWN) {
                current + Coords(0, command.magnitude)
            } else {
                current + Coords(command.magnitude, 0)
            }
        })
    }

    fun pilotWithAim(commands: List<SubCommand>): Coords {
        var aim = 0
        return commands.fold(Coords(0, 0), { current, command ->
            if (command.direction == Direction.UP) {
                aim -= command.magnitude
                current
            } else if (command.direction == Direction.DOWN) {
                aim += command.magnitude
                current
            } else {
                current + Coords(command.magnitude, aim * command.magnitude)
            }
        })
    }

    companion object {
        fun parseInput(input: String): List<SubCommand> {
            return input.split("\n")
                .filter { line -> line.isNotEmpty() }
                .map { line ->
                    val (dir, amt) = line.split(" ", limit = 2)
                    SubCommand(toDirection(dir), amt.toInt())
                }
        }

        fun toDirection(input: String): Direction {
            return if (input == "forward") {
                Direction.FORWARD
            } else if (input == "down") {
                Direction.DOWN
            } else {
                Direction.UP
            }
        }
    }

    enum class Direction {
        FORWARD, DOWN, UP
    }

    data class SubCommand(val direction: Direction, val magnitude: Int)

    class Coords(val range: Int, val depth: Int) {
        operator fun plus(other: Coords): Coords {
            return Coords(range + other.range, depth + other.depth)
        }
    }
}
