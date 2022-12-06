import java.util.Stack

fun main() {
    fun part1(input: List<String>): String {
        val chunks = input.first().split("\n").map { line ->
            val chunk = line.chunked(4)
            chunk
        }
        val rows = chunks.last()
        val stacks = rows.map { Stack<String>() }
        val c = chunks.filterNot { it == rows }
        c.reversed().map {
            it.forEachIndexed { i, v ->
                if (v.trim().isNotEmpty()) stacks[i].push(v.trim())
            }
        }
        val commands = input[1].split("\n").map {
            it.split(" ")
        }
        commands.filterNot { it[0].isEmpty() }.forEach { command ->
            repeat(command[1].toInt()) {
                val from = command[3].toInt() - 1
                val to = command[5].toInt() - 1
                val pop = stacks[from].pop()
                stacks[to].push(pop)
            }
        }
        var solution = ""
        stacks.forEach {
            solution += it.pop().filter { it.isLetter() }
        }
        return solution
    }

    fun part2(input: List<String>): String {
        val chunks = input.first().split("\n").map { line ->
            val chunk = line.chunked(4)
            chunk
        }
        val rows = chunks.last()
        val stacks = rows.map { Stack<String>() }
        val c = chunks.filterNot { it == rows }
        c.reversed().map {
            it.forEachIndexed { i, v ->
                if (v.trim().isNotEmpty()) stacks[i].push(v.trim())
            }
        }
        val commands = input[1].split("\n").map {
            it.split(" ")
        }
        commands.filterNot { it[0].isEmpty() }.forEach { command ->
            val tempStack = Stack<String>()
            repeat(command[1].toInt()) {
                val from = command[3].toInt() - 1
                tempStack.push(stacks[from].pop())
            }

            repeat(command[1].toInt()) {
                val to = command[5].toInt() - 1
                stacks[to].push(tempStack.pop())
            }
        }
        var solution = ""
        stacks.forEach {
            solution += it.pop().filter { it.isLetter() }
        }
        return solution
    }

    val input = readFileSplitEmptyline("Day05")
    println(part1(input))
    println(part2(input))
    check(part1(input) == "PSNRGBTFT")
    check(part2(input) == "BNTZFPMMW")
}
