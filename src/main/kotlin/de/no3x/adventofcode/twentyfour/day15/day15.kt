package de.no3x.adventofcode.twentyfour.day15

class Day15 {

    fun solve(input: String): Long {

        val rows = input.lines().size
        val columns = input.lines()[0].length

        val (pieces, moves) = transformInput(input)
        val board = Board(rows, columns, pieces)
        board.applyMoves(moves)
        return 14
    }

    class Warehouse

    private fun transformInput(input: String): Pair<MutableList<Piece>, MutableList<Move>> {
        var separatorHit = false
        val pieces = mutableListOf<Piece>()
        val moves = mutableListOf<Move>()
        input.lines()
            .forEachIndexed { lineIndex, line ->
                run {
                    if (line.isEmpty()) {
                        separatorHit = true
                        return@forEachIndexed
                    }
                    if (!separatorHit) {
                        pieces.addAll(line.mapIndexed { cellIndex, cell ->
                            Piece(Position(lineIndex, cellIndex), Symbol.of(cell))
                        })
                    } else {
                        moves.addAll(line.map {
                            Move(Direction.of(it))
                        })
                    }
                }
            }
        return pieces to moves
    }

}

class Board(val rows: Int, val columns: Int, val pieces: MutableList<Piece>) {

    fun getRobotPiece(): Piece {
        return pieces.first { it.symbol == Symbol.ROBOT }
    }

    fun applyMoves(moves: List<Move>) {
        moves.forEach { move -> tryMoveRobot(move) }
    }

    private fun tryMoveRobot(move: Move) {
        val tryPosition = getRobotPiece().position + move.direction
    }

}

class Position(val row: Int, val column: Int) : Vec2D(row, column) {
    operator fun plus(direction: Direction): Position {
        return Position(this + direction.eigenvector)
    }

    constructor(vec2D: Vec2D) : this(vec2D.x, vec2D.y)
}

open class Vec2D(val x: Int, val y: Int) {
    operator fun plus(vec2D: Vec2D): Vec2D {
        return Vec2D(this.x + vec2D.x, this.y + vec2D.y)
    }
}

class Piece(val position: Position, val symbol: Symbol) {

}

enum class Symbol(private val char: Char) {
    ROBOT('@'),
    BOX('O'),
    EMPTY('.'),
    WALL('#');

    companion object {
        fun of(it: Char): Symbol = Symbol.entries.first { s -> s.char == it }
    }
}

class Move(val direction: Direction) {
}

enum class Direction(private val char: Char, val eigenvector: Vec2D) {
    U('^', Vec2D(-1, 0)),
    R('>', Vec2D(0, 1)),
    D('v', Vec2D(1, 0)),
    L('<', Vec2D(1, -1));

    companion object {
        fun of(it: Char): Direction = Direction.entries.first { s -> s.char == it }
    }
}
