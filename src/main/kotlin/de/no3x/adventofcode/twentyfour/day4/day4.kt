package de.no3x.adventofcode.twentyfour.day4

class Day4 {

    fun solve(matrix: List<List<Char>>): Int {
        val allChars = mutableListOf<Char>()
        var currentMatrix = matrix

        // apply counts for each rotation of the matrix, this way we can match forward and reverse spelled word
        repeat(4) {
            val rotatedMatrix = rotateMatrix90CW(currentMatrix)
            val diagonalChars = collectDiagonally(rotatedMatrix)

            // contains rows separated by '|' so words do not chain across lines
            val matrixChars = rotatedMatrix.flatMap { it + '|' }

            // collect
            allChars.addAll(diagonalChars.toList())
            allChars.addAll(matrixChars)

            currentMatrix = rotatedMatrix
        }

        return countOccurrences(allChars.joinToString(""), "XMAS")
    }
}

fun countOccurrences(str: String, sub: String): Int {
    return str.windowed(sub.length) { it == sub }.count { it }
}

inline fun <reified T> rotateMatrix90CW(matrix: List<List<T>>): List<List<T>> {
    val n = matrix.size
    // Create a new list of lists with the same size, filled with the first element
    val rotated = List(n) { MutableList(n) { matrix[0][0] } }

    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            rotated[j][n - 1 - i] = matrix[i][j]
        }
    }
    return rotated
}

fun collectDiagonally(matrix: List<List<Char>>): CharArray {
    val chars = mutableListOf<Char>()
    val queue = ArrayDeque<Iterator<Char>>()
    var pos = 0

    do {
        if (pos < matrix.size) {
            queue.addFirst(matrix[pos].iterator())
        }

        var size = queue.size
        while (--size >= 0) {
            val current = queue.removeFirst()
            chars.add(current.next())

            if (current.hasNext()) {
                queue.addLast(current)
            }
        }
        // add any char as separator so words do not chain across diagonals
        chars.add('|')
        pos++
    } while (queue.isNotEmpty() || pos < matrix.size)

    return chars.toCharArray()
}