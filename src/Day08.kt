fun main() {
    fun part1(input: List<String>) {
        val copyInput = input.map { it }
        var trees = input.first().count() + input.last().count()
        input.dropLast(1).drop(1).forEachIndexed { index, row ->
            row.forEachIndexed { i, currentTree ->
                // check trees to the left
                if (row.substring(0, i).none { leftTree -> leftTree.code >= currentTree.code }) {
//                    println("adding 1 for tree $currentTree visible from the left")
                    trees += 1
                    return@forEachIndexed
                }
                // check trees to the right
                if (row.substring(i+1).none { leftTree -> leftTree.code >= currentTree.code }) {
//                    println("adding 1 for tree $currentTree visible from the right")
                    trees += 1
                    return@forEachIndexed
                }
                // check trees to the top
                if (copyInput.take(index+1).map { it[i]}.none{ topTree -> topTree.code >= currentTree.code}) {
//                    println("adding 1 for tree $currentTree visible from the top")
                    trees += 1
                    return@forEachIndexed
                }
                // check trees to the bottom
                if (copyInput.drop(index+2).map { it[i]}.none{ bottomTree -> bottomTree.code >= currentTree.code}) {
//                    println("adding 1 for tree $currentTree visible from the bottom")
                    trees += 1
                    return@forEachIndexed
                }
            }
        }
        println("$trees trees visible from outside the grid")
        return
    }

    fun part2(input: List<String>) {
        val copyInput = input.map { it }
        var bestScore = 0
        var bestPosition = Pair(0, 0)

        input.forEachIndexed { y, row ->
            row.forEachIndexed { x, currentTree ->
                var left = 0
                var right = 0
                var top = 0
                var bottom = 0
                row.substring(0, x).reversed().firstOrNull {
                        left += 1;
                        it.code >= currentTree.code
                    }
                row.substring(x+1).firstOrNull {
                    right += 1;
                    it.code >= currentTree.code
                }
                copyInput.take(y).map { it[x]}.reversed().firstOrNull {
                    top += 1;
                    it.code >= currentTree.code
                }
                copyInput.drop(y+1).map { it[x]}.firstOrNull {
                    bottom += 1;
                    it.code >= currentTree.code
                }
//                println("tree $currentTree top: $top bottom: $bottom right: $right left: $left")
                val scenicScore = top * bottom * left * right
                if ( scenicScore > bestScore) {
                    bestScore = scenicScore
                    bestPosition = Pair(x+1, y+1)
                }
            }
        }
        println("Best scenicScore $bestScore in pos $bestPosition")
        return
    }

    val input = readInput("Day08")
    part1(input)
    part2(input)
}
