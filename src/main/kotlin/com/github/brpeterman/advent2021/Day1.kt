package com.github.brpeterman.advent2021

class Day1 {
    fun measureIncreases(input: List<Int>, windowSize: Int = 1): Int {
        // Find the number of increases from previous
        var lastSum = input.slice(0..windowSize-1).sum()
        var count = 0
        for (i in windowSize..input.size-1) {
            val nextSum = lastSum + input[i] - input[i-windowSize]
            if (nextSum > lastSum) {
                count++
            }
            lastSum = nextSum
        }
        return count
    }

    companion object {
        fun parseInput(rawInput: String): List<Int> {
            return rawInput.split("\n")
                .filter { s -> s.isNotEmpty() }
                .map { s -> s.toInt() }
        }
    }
}
