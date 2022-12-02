import java.util.IllegalFormatException

// rules
//        A = Rock
//        B = Paper
//        C = SCissors
//
//        X = Rock 1
//        Y = Paper 2
//        Z = Scissors 3

//        looses = 0
//        draw = 3
//        win = 6
fun main() {
    val ROCK = 1
    val PAPER = 2
    val SCISSORS = 3
    val LOOSE = 0
    val DRAW = 3
    val WIN = 6

    fun calculateScore(input: List<List<String>>): Int {
        var sum = 0
        input.forEach {
            when (it[1]) {
                "X" -> {
                    sum += ROCK
                    when (it[0]) {
                        "A" -> sum += DRAW
                        "B" -> sum += LOOSE
                        "C" -> sum += WIN
                    }
                }
                "Y" -> {
                    sum += PAPER
                    when (it[0]) {
                        "A" -> sum += WIN
                        "B" -> sum += DRAW
                        "C" -> sum += LOOSE
                    }
                }
                "Z" -> {
                    sum += SCISSORS
                    when (it[0]) {
                        "A" -> sum += LOOSE
                        "B" -> sum += WIN
                        "C" -> sum += DRAW
                    }
                }
            }
        }
        return sum
    }

    fun part1(input: List<String>): Int {
        var arr = input.map{
            it.split(" ")
        }
        return calculateScore(arr)
    }

    fun part2(input: List<String>): Int {
        var arr = input.map{
            it.split(" ")
        }
        // X -> loose
        // Y -> draw
        // Z -> win
        val strategy = arr.map { x ->
            when (x[1]) {
                "X" -> {
                    // loose
                    when (x[0]) {
                        "A" -> listOf("A", "Z")
                        "B" -> listOf("B", "X")
                        "C" -> listOf("C", "Y")
                        else -> throw IllegalArgumentException("unknown")
                    }
                }
                "Y" -> {
                    // draw
                    when (x[0]) {
                        "A" -> listOf("A", "X")
                        "B" -> listOf("B", "Y")
                        "C" -> listOf("C", "Z")
                        else -> throw IllegalArgumentException("unknown")
                    }
                }
                "Z" -> {
                    // win
                    when (x[0]) {
                        "A" -> listOf("A", "Y")
                        "B" -> listOf("B", "Z")
                        "C" -> listOf("C", "X")
                        else -> throw IllegalArgumentException("unknown")
                    }
                }
                else -> throw IllegalArgumentException("unknown")
            }
        }
        return calculateScore(strategy)
    }

    val input = readInput("Day02")
    check(part1(input) == 13268)
    check(part2(input) == 15508)
}
