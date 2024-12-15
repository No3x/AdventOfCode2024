package de.no3x.adventofcode.twentyfour.day15

import com.google.common.collect.HashBiMap

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
            .forEach { line ->
                run {
                    if (line.isEmpty()) {
                        separatorHit = true
                        return@forEach
                    }
                    if (!separatorHit) {
                        pieces.addAll(line.map {
                            Piece(Symbol.of(it))
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

fun <K, V> Map<K, V>.toBiMap() = HashBiMap.create(this)

class Board {
    val rows: Int
    val columns: Int
    val pieces: MutableList<Piece>
    val positions: MutableMap<Piece, Position>

    constructor(rows: Int, columns: Int, pieces: MutableList<Piece>) {
        this.rows = rows
        this.columns = columns
        this.pieces = pieces

        val map = mutableMapOf<Piece, Position>()
        for ((rowIndex, piecesInRow) in pieces.chunked(columns).withIndex()) {
            for ((colIndex, piece) in piecesInRow.withIndex()) {
                val position = Position(rowIndex, colIndex)
                map[piece] = position
            }
        }
        this.positions = map
    }

    private fun getRobotPiece(): Piece {
        return pieces.first { it.symbol == Symbol.ROBOT }
    }

    fun applyMoves(moves: List<Move>) {
        moves.forEach { move -> tryMoveRobot(move) }
    }

    private fun Piece.position(): Position {
        return positions[this]!!
    }

    fun tryMoveRobot(move: Move): Boolean {
        val robotPosition = getRobotPiece().position()
        val tryPosition = robotPosition + move.direction
        val pieceOnTryPosition = positions.toBiMap().inverse()[tryPosition]!!

        println("Try to move to a position with piece of symbol ${pieceOnTryPosition.symbol}.")
        return when (pieceOnTryPosition.symbol) {
            Symbol.WALL -> {
                return false
            }
            Symbol.EMPTY -> {
                positions.replace(pieceOnTryPosition, robotPosition)
                //positions.toBiMap().inverse().put(robotPosition, pieceOnTryPosition)
                positions.replace(getRobotPiece(), tryPosition)
                //positions.toBiMap().inverse().put(tryPosition, getRobotPiece())
                println(tryPosition)
                return true
            }
            Symbol.BOX -> {

            }
            Symbol.ROBOT -> {
                throw IllegalStateException("There is only one robot on the board so no position we try to move to should contain a robot.")
            }
        }
    }

}

data class Position(val row: Int, val column: Int) : Vec2D(row, column) {
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

class Piece(val symbol: Symbol) {

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

data class Move(val direction: Direction) {
}

enum class Direction(private val char: Char, val eigenvector: Vec2D) {
    U('^', Vec2D(-1, 0)),
    R('>', Vec2D(0, 1)),
    D('v', Vec2D(1, 0)),
    L('<', Vec2D(0, -1));

    companion object {
        fun of(it: Char): Direction = Direction.entries.first { s -> s.char == it }
    }
}
