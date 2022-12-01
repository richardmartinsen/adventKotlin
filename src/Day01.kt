fun main() {
    fun part1(input: List<String>): Int {
        return input.foldIndexed(Pair(listOf<List<Int>>(), listOf<Int>())) { idx, acc, line ->
            when {
                idx + 1 == input.size -> {
                    Pair(acc.first + listOf(acc.second + line.toInt()), listOf())
                }

                line.isEmpty() -> {
                    Pair(acc.first + listOf(acc.second), listOf())
                }

                else -> {
                    Pair(acc.first, acc.second + line.toInt())
                }
            }
        }.first.maxOf { list ->
            list.sumOf { it }
        }
    }

    fun part2(input: List<String>): Int {
        return input.foldIndexed(Pair(listOf<List<Int>>(), listOf<Int>())) { idx, acc, line ->
            when {
                idx + 1 == input.size -> {
                    Pair(acc.first + listOf(acc.second + line.toInt()), listOf())
                }

                line.isEmpty() -> {
                    Pair(acc.first + listOf(acc.second), listOf())
                }

                else -> {
                    Pair(acc.first, acc.second + line.toInt())
                }
            }
        }.first.map { list ->
            list.sumOf { it }
        }.sortedDescending().take(3).sum()
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
    check(part1(input) == 70296)
    check(part2(input) == 205381)
}
