package com.github.brpeterman.advent2021

class Day13 {
    fun foldAll(manual: Manual): Manual {
        var currentManual = manual
        while (currentManual.folds.isNotEmpty()) {
            currentManual = fold(currentManual)
        }
        return currentManual
    }

    fun fold(manual: Manual): Manual {
        val nextFold = manual.folds.first()
        val newDots = HashSet<Coords>()
        val dimension = if (nextFold.axis == Axis.X) {
            Coords::x
        } else {
            Coords::y
        }
        manual.dots.forEach { dot ->
            if (dimension(dot) > nextFold.position) {
                val newPos = ((dimension(dot) - nextFold.position) * -1) + nextFold.position
                val newDot  = if (nextFold.axis == Axis.X) {
                    Coords(newPos, dot.y)
                } else {
                    Coords(dot.x, newPos)
                }
                newDots.add(newDot)
            } else if (dimension(dot) < nextFold.position) {
                newDots.add(dot)
            }
            // Ignore anything directly on the line
        }
        return Manual(newDots, manual.folds.drop(1))
    }

    companion object {
        fun parseInput(input: String): Manual {
            val (dotLines, foldLines) = input.split("\n\n").take(2)
            val dots = dotLines.split("\n")
                .filter { it.isNotEmpty() }
                .map { line ->
                    val (x, y) = line.split(",").take(2)
                    Coords(x.toInt(), y.toInt())
                }
                .toSet()
            val folds = foldLines.split("\n")
                .filter { it.isNotEmpty() }
                .map { line ->
                    val (axis, position) = line.split(" ", "=").takeLast(2)
                    Fold(position.toInt(), Axis.fromString(axis))
                }
            return Manual(dots, folds)
        }
    }

    class Manual(val dots: Set<Coords>, val folds: List<Fold>) {
        override fun toString(): String {
            val maxX = dots.maxByOrNull { it.x }!!.x
            val maxY = dots.maxByOrNull { it.y }!!.y
            var out = ""
            for (y in 0..maxY) {
                var line = ""
                for (x in 0..maxX) {
                    line += if (dots.contains(Coords(x, y))) "##" else ".."
                }
                out += line + "\n"
            }
            return out
        }
    }

    data class Coords(val x: Int, val y: Int)

    data class Fold(val position: Int, val axis: Axis)

    enum class Axis {
        X, Y;

        companion object {
            fun fromString(value: String): Axis {
                return if (value == "x") {
                    X
                } else {
                    Y
                }
            }
        }
    }
}
