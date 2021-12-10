package com.github.brpeterman.advent2021

import java.util.*

class Day10 {
    fun scoreErrors(lines: List<List<String>>): Long {
        return lines.fold(0L, {totalScore, line -> totalScore + scoreLineError(line)})
    }

    fun scoreLineError(line: List<String>): Long {
        val stack = LinkedList<String>()
        for (char in line) {
            if (CHUNK_DELIMITERS.keys.contains(char)) {
                stack.push(char)
            } else {
                val chunkStart = stack.pop()
                if (char != CHUNK_DELIMITERS[chunkStart]) {
                    return SCORES[char]!!
                }
            }
        }
        return 0
    }

    fun scoreCompletions(lines: List<List<String>>): Long {
        val scores = lines.map { scoreLineCompletion(it) }
            .filter { it != 0L }
            .sorted()
        return scores[scores.size / 2]
    }

    fun scoreLineCompletion(line: List<String>): Long {
        val stack = LinkedList<String>()
        for (char in line) {
            if (CHUNK_DELIMITERS.keys.contains(char)) {
                stack.push(char)
            } else {
                val chunkStart = stack.pop()
                if (char != CHUNK_DELIMITERS[chunkStart]) {
                    return 0 // Corrupt line, doesn't count
                }
            }
        }
        return stack.fold(0L, { totalScore, char -> totalScore * 5 + SCORES[CHUNK_DELIMITERS[char]!!]!! })
    }

    companion object {
        val CHUNK_DELIMITERS = mapOf(
            Pair("(", ")"),
            Pair("[", "]"),
            Pair("{", "}"),
            Pair("<", ">")
        )
        val SCORES = mapOf(
            Pair(")", 3L),
            Pair("]", 57L),
            Pair("}", 1197L),
            Pair(">", 25137L),
            Pair(")", 1L),
            Pair("]", 2L),
            Pair("}", 3L),
            Pair(">", 4L)
        )

        fun parseInput(input: String): List<List<String>> {
            return input.split("\n")
                .filter { it.isNotEmpty() }
                .map { line ->
                    line.split("")
                        .filter { it.isNotEmpty() }
                }
        }
    }
}
