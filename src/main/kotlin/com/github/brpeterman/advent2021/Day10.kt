package com.github.brpeterman.advent2021

import java.util.*

class Day10 {
    fun scoreErrors(lines: List<List<String>>): Long {
        return lines.fold(0L, {totalScore, line -> totalScore + scoreLineError(line)})
    }

    fun scoreLineError(line: List<String>): Long {
        return scoreLine(line, { char -> ERROR_SCORES[char]!! }, { 0 })
    }

    fun scoreCompletions(lines: List<List<String>>): Long {
        val scores = lines.map { scoreLineCompletion(it) }
            .filter { it != 0L }
            .sorted()
        return scores[scores.size / 2]
    }

    fun scoreLineCompletion(line: List<String>): Long {
        return scoreLine(line, { 0 }, { stack -> stack.fold(0L, { totalScore, char -> totalScore * 5 + COMPLETION_SCORES[CHUNK_DELIMITERS[char]!!]!! })})
    }

    fun scoreLine(line: List<String>, errorScorer: (String) -> Long, completionScorer: (List<String>) -> Long): Long {
        val stack = LinkedList<String>()
        for (char in line) {
            if (CHUNK_DELIMITERS.keys.contains(char)) {
                stack.push(char)
            } else {
                val chunkStart = stack.pop()
                if (char != CHUNK_DELIMITERS[chunkStart]) {
                    return errorScorer(char)
                }
            }
        }
        return completionScorer(stack)
    }

    companion object {
        val CHUNK_DELIMITERS = mapOf(
            Pair("(", ")"),
            Pair("[", "]"),
            Pair("{", "}"),
            Pair("<", ">")
        )
        val ERROR_SCORES = mapOf(
            Pair(")", 3L),
            Pair("]", 57L),
            Pair("}", 1197L),
            Pair(">", 25137L)
        )
        val COMPLETION_SCORES = mapOf(
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
