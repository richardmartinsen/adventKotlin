fun main() {
    fun findPairs(input: List<String>) =
        input.map { line ->
            line.split(",")
                .map { pair ->
                    pair.split("-")
                }.map { values ->
                    val a = values[0].toInt()
                    val b = values[1].toInt()
                    a to b
                }
        }

    fun part1(input: List<String>) =
        findPairs(input).filter {
            it[0].overlapComplete(it[1])
        }.count()

    fun part2(input: List<String>) =
        findPairs(input).filter {
            it[0].overlapSome(it[1])
        }.count()

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
    check(part1(input) == 605)
    check(part2(input) == 914)
}

private fun Pair<Int, Int>.overlapComplete(other: Pair<Int, Int>): Boolean {
    if ((this.first >= other.first) && (this.second <= other.second)) return true
    if ((other.first >= this.first) && (other.second <= this.second)) return true
    return false
}

private fun Pair<Int, Int>.overlapSome(other: Pair<Int, Int>): Boolean {
    if ((this.second < other.first) || (this.first > other.second)) return false
    return true
}
//private fun <A, B> Pair<A, B>.overlap() {
//    TODO("Not yet implemented")
//}
