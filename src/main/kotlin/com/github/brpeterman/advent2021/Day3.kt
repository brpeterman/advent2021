package com.github.brpeterman.advent2021

class Day3 {
    fun powerConsumption(codes: List<String>): Int {
        var binaryGamma = ""
        val codeLength = codes[0].length
        for (position in 0..codeLength-1) {
            binaryGamma += mostCommonForPosition(codes, position)
        }
        val gamma = Integer.parseInt(binaryGamma, 2)
        val epsilon = gamma.inv() and ((1 shl codeLength) - 1)
        return gamma * epsilon
    }

    fun lifeSupportRating(codes: List<String>): Int {
        val generator = filterByBitCriteria(codes, true)
        val scrubber = filterByBitCriteria(codes, false)
        return generator * scrubber
    }

    fun mostCommonForPosition(codes: List<String>, position: Int): Int {
        var balance = 0
        codes.forEach { code ->
            if (code[position] == '0') {
                balance += 1
            } else {
                balance -= 1
            }
        }
        return if (balance > 0) 0 else 1
    }

    fun filterByBitCriteria(codes: List<String>, mostCommon: Boolean): Int {
        val criteria = if (mostCommon) {
            { a: String, b: String -> a == b }
        } else {
            { a: String, b: String -> a != b }
        }

        var filteredCodes = codes
        var position = 0
        while (filteredCodes.size > 1) {
            val mostCommon = mostCommonForPosition(filteredCodes, position).toString()
            filteredCodes = filteredCodes.filter { code -> criteria(code[position].toString(), mostCommon) }
            position += 1
        }
        return Integer.parseInt(filteredCodes[0], 2)
    }

    companion object {
        fun parseInput(input: String): List<String> {
            return input.split("\n")
                .filter { line -> line.isNotEmpty() }
        }
    }
}
