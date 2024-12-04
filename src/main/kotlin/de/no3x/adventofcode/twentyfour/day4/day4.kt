package de.no3x.adventofcode.twentyfour.day4

private const val SEPARATOR = '|'

class Day4 {

    fun solve(matrix: List<List<Char>>): Int {
        val allChars = mutableListOf<Char>()

        // apply counts for 2 sides of the matrix. this way we collect sequence of chars in any direction and diagonals
        // optimization applied (was 4 rotations): with combined match to find XMAS and SAMX later on we do need only 1 rotation and not 4 rotations
        allChars.addAll(collectDiagonally(matrix).toList())
        allChars.addAll(matrix.flatMap { it + SEPARATOR })

        val rotatedMatrix = rotateMatrix90CW(matrix)
        allChars.addAll(collectDiagonally(rotatedMatrix).toList())
        allChars.addAll(rotatedMatrix.flatMap { it + SEPARATOR })

        return countOccurrences(allChars.joinToString(""), "XMAS")
    }
}

fun countOccurrences(str: String, sub: String): Int {
    return str.windowed(sub.length) { it == sub || it == sub.reversed() }.count { it }
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
        chars.add(SEPARATOR)
        pos++
    } while (queue.isNotEmpty() || pos < matrix.size)

    return chars.toCharArray()
}