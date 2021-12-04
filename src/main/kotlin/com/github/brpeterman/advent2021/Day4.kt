package com.github.brpeterman.advent2021

class Day4 {
    fun play(numbers: List<Int>, boards: List<BingoBoard>): Int {
        for (num in numbers) {
            boards.forEach { board ->
                board.mark(num)
                val score = board.score()
                if (score > 0) {
                    return score * num
                }
            }
        }
        return 0
    }

    fun lose(numbers: List<Int>, boards: List<BingoBoard>): Int {
        val remainingBoards = (0..boards.size-1).toHashSet()
        for (num in numbers) {
            boards.forEachIndexed { index, board ->
                if (remainingBoards.contains(index)) {
                    board.mark(num)
                    val score = board.score()
                    if (score > 0) {
                        if (remainingBoards.size > 1) {
                            remainingBoards.remove(index)
                        } else {
                            return board.score() * num
                        }
                    }
                }
            }
        }
        return 0
    }

    companion object {
        fun parseInput(input: String): Pair<List<Int>, List<BingoBoard>> {
            val segments = input.split("\n\n")
            val calledNumbers = segments[0]
                .split(",")
                .map { s -> s.toInt() }
            val boards = segments.drop(1)
                .map { boardInput -> BingoBoard(boardInput) }
            return Pair(calledNumbers, boards)
        }
    }

    class BingoBoard(input: String) {
        val numbers = HashMap<Int, Coords>()
        val numbersInverse = HashMap<Coords, Int>()
        val marked = HashSet<Coords>()

        init {
            var row = 0
            input.split("\n")
                .filter { line -> line.isNotEmpty() }
                .forEach { line ->
                    var col = 0
                    line.split("""\s+""".toRegex())
                        .filter { num -> num.isNotEmpty() }
                        .forEach { num ->
                            numbers.put(num.toInt(), Coords(row, col))
                            numbersInverse.put(Coords(row, col), num.toInt())
                            col += 1
                        }
                    row += 1
                }
        }

        fun mark(num: Int) {
            if (numbers.containsKey(num)) {
                marked.add(numbers[num]!!)
            }
        }

        fun score(): Int {
            var win = false
            var score = 0
            for (row in (0..4)) {
                var markedCount = 0
                for (col in (0..4)) {
                    if (!marked.contains(Coords(row, col))) {
                        score += numbersInverse[Coords(row, col)]!!
                    } else {
                        markedCount += 1
                    }
                }
                if (markedCount == 5) {
                    win = true
                }
            }

            for (col in 0..4) {
                var markedCount = 0
                for (row in (0..4)) {
                    if (marked.contains(Coords(row, col))) {
                        markedCount += 1
                    }
                }
                if (markedCount == 5) {
                    win = true
                }
            }

            return if (win) score else 0
        }
    }

    data class Coords(val row: Int, val col: Int)
}
