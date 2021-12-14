package com.github.brpeterman.advent2021

class Day14 {
    fun synthesize(formula: Formula, steps: Int): Map<String, Long> {
        var pairs = formula.template.split("").filter { it.isNotEmpty() }
            .windowed(2, 1)
            .fold(HashMap<String, Long>().withDefault { 0 }, { map, pair ->
                val strPair = pair.joinToString("")
                val count = map.getValue(strPair)
                map[strPair] = count + 1
                map
            })
        (1..steps).forEach {
            pairs = synthesize(pairs, formula.rules).toMutableMap()
        }
        return pairs.entries.fold(HashMap<String, Long>().withDefault { 0 }, { chars, entry ->
            val (l, r) = entry.key.split("").filter { it.isNotEmpty() }.take(2)
            chars[l] = chars.getValue(l) + entry.value
            chars[r] = chars.getValue(r) + entry.value
            chars })
            .entries
            .map { entry -> Pair(entry.key, Math.ceil(entry.value / 2.0).toLong()) }
            .toMap()
    }

    fun synthesize(originalPairs: Map<String, Long>, rules: Map<String, String>): Map<String, Long> {
        val newPairs = HashMap<String, Long>().withDefault { 0 }
        for (pair in originalPairs) {
            if (rules.containsKey(pair.key)) {
                val newElem = rules[pair.key]!!
                val leftPair = pair.key[0] + newElem
                val rightPair = newElem + pair.key[1]
                newPairs[leftPair] = newPairs.getValue(leftPair) + pair.value
                newPairs[rightPair] = newPairs.getValue(rightPair) + pair.value
            } else {
                newPairs[pair.key] = newPairs.getValue(pair.key) + pair.value
            }
        }
        return newPairs
    }

    companion object {
        fun parseInput(input: String): Formula {
            val (template, rulesInput) = input.split("\n\n").take(2)
            val rules = rulesInput.split("\n")
                .filter { it.isNotEmpty() }
                .map { line ->
                    val (left, right) = line.split(" -> ").take(2)
                    Pair(left, right)
                }
                .toMap()
            return Formula(template, rules)
        }
    }

    class Formula(val template: String, val rules: Map<String, String>)
}
