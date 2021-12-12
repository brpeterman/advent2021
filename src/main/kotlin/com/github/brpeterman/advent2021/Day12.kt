package com.github.brpeterman.advent2021

class Day12 {
    fun countPaths(cave: Cave, revisits: Int = 0): Int {
        // Do an exhaustive depth-first search. Tally every time we get to the end
        val start = cave.rooms.find { it == Room(START_NAME, false) }!!
        val end = cave.rooms.find { it == Room(END_NAME, false) }!!
        return explore(cave, start, end, HashSet(), revisits)
    }

    fun explore(cave: Cave, start: Room, end: Room, visited: MutableSet<Room>, revisitsRemaining: Int): Int {
        if (start == end) {
            return 1
        }
        if (!start.majorRoom) {
            visited.add(start)
        }
        val toVisit = cave.connections[start]!!.filter { room -> !visited.contains(room) || canRevisit(room, revisitsRemaining) }
        if (toVisit.isEmpty()) {
            return 0
        }
        return toVisit.fold(0, { totalPaths, room -> totalPaths + explore(cave, room, end, HashSet(visited), if (visited.contains(room)) revisitsRemaining - 1 else revisitsRemaining)})
    }

    fun canRevisit(room: Room, revisitsRemaining: Int): Boolean {
        return room.name != START_NAME &&
                room.name != END_NAME &&
                !room.majorRoom &&
                revisitsRemaining > 0
    }

    companion object {
        val START_NAME = "start"
        val END_NAME = "end"

        fun parseInput(input: String): Cave {
            val rooms = HashSet<Room>()
            val connections = HashMap<Room, MutableSet<Room>>().withDefault { HashSet() }
            input.split("\n")
                .filter { it.isNotEmpty() }
                .forEach { line ->
                    val (name1, name2) = line.split("-").take(2)
                    val room1 = Room(name1, name1.toUpperCase() == name1)
                    val room2 = Room(name2, name2.toUpperCase() == name2)
                    rooms.add(room1)
                    rooms.add(room2)
                    val room1Connections = connections.getValue(room1)
                    connections[room1] = (room1Connections + room2).toMutableSet()
                    val room2Connections = connections.getValue(room2)
                    connections[room2] = (room2Connections + room1).toMutableSet()
                }
            return Cave(rooms, connections)
        }
    }

    class Cave(val rooms: Set<Room>, val connections: Map<Room, Set<Room>>)

    data class Room(val name: String, val majorRoom: Boolean)
}
