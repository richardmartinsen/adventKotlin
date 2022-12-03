fun main() {
    fun findDuplicate(first: String, second: String): Char {
        first.forEach {
            if (second.contains(it)) {
                return it
            }
        }
        return 'X'
    }

    fun findTriplicate(first: String, second: String, third: String): Char {
        first.forEach {
            if (second.contains(it) && third.contains(it)) {
                return it
            }
        }
        return 'X'
    }

    fun part1(input: List<String>): Int {
        val commonItems = mutableListOf<Char>()
        var sum = 0
        input.forEach { text ->
            val first = text.substring(0, (text.length/2))
            val second = text.substring((text.length/2))

            commonItems.add(findDuplicate(first, second))
            println("first : ${first}, second : $second,  common : ${findDuplicate(first, second)}")
        }
        commonItems.forEach{
            if (it.isLowerCase()) {
                sum += it.code - 96
            }
            if (it.isUpperCase()) {
                sum += it.code - 38
                println("$it : ${it.code - 38}")
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val list = input.chunked(3)
        val commonItems = mutableListOf<Char>()
        var sum = 0
        list.forEach { threesome ->
            val first = threesome.first()
            val second = threesome[1]
            val third = threesome[2]
            println("first : ${first}, second : $second, third : $third,  common : ${findTriplicate(first, second, third)}")

            commonItems.add(findTriplicate(first, second, third))
        }
        commonItems.forEach{
            if (it.isLowerCase()) {
                sum += it.code - 96
            }
            if (it.isUpperCase()) {
                sum += it.code - 38
            }
        }
        return sum
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
    check(part1(input) == 7848)
    check(part2(input) == 2616)
}
