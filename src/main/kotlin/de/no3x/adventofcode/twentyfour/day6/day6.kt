package de.no3x.adventofcode.twentyfour.day6

data class Input(
    val rules: List<Pair<Int, Int>>, val updates: List<List<Int>>
)

enum class Orientation { UP, RIGHT, DOWN, LEFT }

var orientation = Orientation.UP

class Day6 {

    fun solve(input: List<String>): Int {

        var matrix = parseMatrix(input)
        var (row, column) = findElement(matrix)
        var i = 0
        while (true) {
            if (matrix[row][column] != 'X') {
                i++
            }
            matrix[row][column] = 'X'
            val (newRow, newColumn) = getPossibleNextTarget(matrix, row, column)
            if ((newRow < 0 || newRow > matrix.size - 1) || (newColumn < 0 || newColumn > matrix[0].size - 1)) {
                return i
            } else if (matrix[newRow][newColumn] == '#') {
                orientation = when (orientation) {
                    Orientation.UP -> Orientation.RIGHT
                    Orientation.RIGHT -> Orientation.DOWN
                    Orientation.DOWN -> Orientation.LEFT
                    else -> Orientation.UP
                }
            } else {
                row = newRow
                column = newColumn
            }
        }

        return i
    }

    private fun getPossibleNextTarget(matrix: Array<CharArray>, row: Int, column: Int): Pair<Int, Int> {

        val stepRow = when (orientation) {
            Orientation.UP -> -1
            Orientation.RIGHT -> 0
            Orientation.DOWN -> 1
            Orientation.LEFT -> 0
        }
        val stepCell = when (orientation) {
            Orientation.UP -> 0
            Orientation.RIGHT -> 1
            Orientation.DOWN -> 0
            Orientation.LEFT -> -1
        }
        val row = Math.min(row + stepRow, matrix.size)
        val column = Math.min(column + stepCell, matrix.get(0).size)
        return row to column
    }

    private fun findElement(matrix: Array<CharArray>): Pair<Int, Int> {
        for ((rowIndex, numberArray) in matrix.withIndex()) {
            for ((cellIndex, number) in numberArray.withIndex()) {
                if (number.equals('^'))
                    return rowIndex to cellIndex
            }
        }
        throw IllegalStateException("Could not find element")
    }

    private fun parseMatrix(input: List<String>): Array<CharArray> {
        val matrix: Array<CharArray> = Array(input.size) {
            CharArray(input.get(0).length)
        }
        for ((rowIndex, line) in input.withIndex()) {
            for ((cellIndex, cell) in line.toCharArray().withIndex()) {
                matrix[rowIndex][cellIndex] = cell
            }
        }
        return matrix
    }

}
