fun main() {
    fun findStartOfMessage(input: String, len: Int): Int {
        val window = input.windowed(len)
        window.forEachIndexed { index, win ->
            if (win.toList().distinct().size == len) {
                println("index : ${index+len} window : $win ")
                return index + len
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val input = readInput("Day06")

    input.forEach {
        findStartOfMessage(it, 4)
    }
    input.forEach {
        findStartOfMessage(it, 14)
    }
}
