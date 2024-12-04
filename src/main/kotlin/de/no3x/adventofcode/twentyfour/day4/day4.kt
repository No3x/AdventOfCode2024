package de.no3x.adventofcode.twentyfour.day4

class Day4 {

    fun solve(matrix: List<List<Char>>): Int {
        val chars = mutableListOf<Char>()
        var currentMatrix = matrix
        repeat(4) {
            val currentMatrixChars : List<Char> = currentMatrix.flatMap { it + '|' }
            val segmentChars = collectDiagonally(currentMatrix)
            chars.addAll(segmentChars.toList())
            chars.addAll(currentMatrixChars)
            val rotated = rotateMatrix90CW(currentMatrix)
            currentMatrix = rotated
        }

        return countOccurrences(chars.joinToString(""), "XMAS")
    }
}

inline fun <reified T> List<List<T>>.to2DArray(): Array<Array<T>> {
    return this.map { it.toTypedArray() }.toTypedArray()
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

fun collectDiagonally(chars: List<List<Char>>): CharArray {
    val ans: MutableList<Char> = ArrayList()
    val queue = ArrayDeque<Iterator<Char>>()
    var pos = 0
    do {
        if (pos < chars.size) {
            queue.addFirst(chars[pos].iterator())
        }
        var sz = queue.size
        while (--sz >= 0) {
            val cur = queue.removeFirst()
            ans.add(cur.next())
            if (cur.hasNext()) {
                queue.addLast(cur)
            }
        }
            // add any char as separator so words do not chain across diagonals
            ans.add('|')
        pos++
    } while (queue.isNotEmpty() || pos < chars.size)
    return ans.toCharArray()
}

