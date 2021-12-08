package com.github.brpeterman.advent2021

class Day8 {
    fun countDigits(entries: List<Pair<List<Set<String>>, List<Set<String>>>>, wantedDigits: List<Int>): Int {
        val allOutputs = entries.fold(listOf<Set<String>>(), { fullList, entry ->
            fullList + entry.second
        })
        val wantedCount = allOutputs.filter { digits ->
            wantedDigits.any { wanted ->
                digits.size == NUMBERS[wanted]!!.size
            }
        }
        return wantedCount.size
    }

    fun decode(entries: List<Pair<List<Set<String>>, List<Set<String>>>>): List<Int> {
        return entries.map { decodeEntry(it.first, it.second) }
    }

    private fun decodeEntry(observedDigits: List<Set<String>>, outputDigits: List<Set<String>>): Int {
        val mappings = easyMappings(observedDigits)
        var remainingDigits = observedDigits - mappings.values

        // Find 9
        val a = (mappings[7]!! - mappings[1]!!).first()
        val nineLessG = mappings[4]!! + setOf(a)
        val nineCode = remainingDigits.filter { digit -> digit.size == 6 && (digit - nineLessG).size == 1}
            .first()
        remainingDigits = remainingDigits.filter { it != nineCode }
        mappings[9] = nineCode

        // Find 5
        val fiveCode = remainingDigits.filter { digit -> digit.size == 5 && (mappings[9]!! - digit).size == 1 && ((mappings[9]!! - digit) - mappings[1]!!).isEmpty() }
            .first()
        remainingDigits = remainingDigits.filter { it != fiveCode }
        mappings[5] = fiveCode

        // Find 3
        val threeCode = remainingDigits.filter { digit -> digit.size == 5 && (mappings[9]!! - digit).size == 1 }
            .first()
        remainingDigits = remainingDigits.filter { it != threeCode }
        mappings[3] = threeCode

        // Find 6
        val sixCode = remainingDigits.filter { digit -> digit.size == 6 && digit != mappings[9]!! && (digit - mappings[5]!!).size == 1 }
            .first()
        remainingDigits = remainingDigits.filter { it != sixCode }
        mappings[6] = sixCode

        // Find 2
        val twoCode = remainingDigits.filter { digit -> digit.size == 5 && (mappings[9]!! - digit).size == 2 }
            .first()
        remainingDigits = remainingDigits.filter { it != twoCode }
        mappings[2] = twoCode

        // Find 0
        mappings[0] = remainingDigits.first()

        // Map to the real numbers
        val inverseMappings = mappings.entries.map {
            Pair(it.value, it.key)
        }.toMap()
        val actualDigits = outputDigits.map { inverseMappings[it] }
        return Integer.parseInt(actualDigits.joinToString(""))
    }

    private fun easyMappings(digits: List<Set<String>>): MutableMap<Int, Set<String>> {
        val mappings = HashMap<Int, Set<String>>()
        listOf(1, 4, 7, 8).forEach { num ->
            mappings[num] = digits.filter { it.size == NUMBERS[num]!!.size }
                .firstOrNull()!!
        }
        return mappings
    }

    companion object {
        val NUMBERS = mapOf(
            Pair(0, toStringSet("abcefg")),
            Pair(1, toStringSet("cf")),
            Pair(2, toStringSet("acdeg")),
            Pair(3, toStringSet("acdfg")),
            Pair(4, toStringSet("bcdf")),
            Pair(5, toStringSet("abdfg")),
            Pair(6, toStringSet("abdefg")),
            Pair(7, toStringSet("acf")),
            Pair(8, toStringSet("abcdefg")),
            Pair(9, toStringSet("abcdfg"))
        )

        fun parseInput(input: String): List<Pair<List<Set<String>>, List<Set<String>>>> {
            return input.split("\n")
                .filter { it.isNotEmpty() }
                .map { line ->
                    val (observed, output) = line.split(" | ")
                    Pair(
                        observed.split(" ")
                            .map { toStringSet(it) },
                        output.split(" ")
                            .map { toStringSet(it) }
                    )
                }
        }

        fun toStringSet(word: String): Set<String> {
            return word.split("")
                .filter { it.isNotEmpty() }
                .toSet()
        }
    }
}
