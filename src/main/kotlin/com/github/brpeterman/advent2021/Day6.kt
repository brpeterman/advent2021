package com.github.brpeterman.advent2021

class Day6 {
    fun simulateGrowth(initialFish: List<Int>, days: Int): Long {
        // Every mature fish will be on one of 7 schedules
        // Keep a count of how many fish are on each schedule
        // Immature fish will reach the main schedules after 2 days
        val groups = initialFish.fold(HashMap<Int, Long>().withDefault { 0 }, { sched, fish ->
            sched[fish] = sched.getValue(fish) + 1
            sched
        })
        for (day in 0..days-1) {
            val readyFish = groups.getValue(day % 7)
            groups[day % 7] = groups.getValue(day % 7) + groups.getValue(7)
            groups[7] = groups.getValue(8)
            groups[8] = readyFish
        }

        return groups.values.sum()
    }

    companion object {
        fun parseInput(input: String): List<Int> {
            return input.split(",")
                .filter { s -> s.isNotEmpty() }
                .map { s -> s.trim().toInt() }
        }
    }
}
