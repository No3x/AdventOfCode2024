package de.no3x.adventofcode.twentyfour.day15

import com.google.common.collect.HashBiMap
import java.lang.Math.pow
import java.lang.Math.sqrt
import kotlin.math.exp

class Day15 {

    fun solve(input: String): Int {

        val rows = input.lines().size
        val columns = input.lines()[0].length

        val (pieces, moves) = transformInput(input)
        val board = Board(rows, columns, pieces)
        board.applyMoves(moves)
        return board.sumOfCoordinates()
    }

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
    val pieces: List<Piece>
    val positions: MutableMap<Piece, Position>

    constructor(rows: Int, columns: Int, pieces: List<Piece>) {
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
        return tryMove(robotPosition, move)
    }

    private fun tryMove(
        robotPosition: Position,
        move: Move
    ): Boolean {
        val tryPosition = robotPosition + move.direction
        val pieceOnTryPosition = positions.toBiMap().inverse()[tryPosition]!!

        println("Try to move to a position with piece of symbol ${pieceOnTryPosition.symbol}.")
        val result = when (pieceOnTryPosition.symbol) {
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
                canPush(pieceOnTryPosition, move.direction)
            }

            Symbol.ROBOT -> {
                throw IllegalStateException("There is only one robot on the board so no position we try to move to should contain a robot.")
            }
        }
        println("Result: $result.")
        return result
    }

    private fun canPush(piece: Piece, direction: Direction): Boolean {
        val tryPosition = piece.position() + direction
        val pieceOnTryPosition = positions.toBiMap().inverse()[tryPosition]!!
        return when (pieceOnTryPosition.symbol) {
            Symbol.WALL -> {
                return false
            }
            Symbol.ROBOT -> throw IllegalStateException("There is only one robot on the board and this case makes no sense.")
            Symbol.BOX -> canPush(pieceOnTryPosition, direction)
            Symbol.EMPTY -> true
        }
    }

    fun sumOfCoordinates(): Int {
        return positions
            .filter { es -> es.key.symbol == Symbol.BOX }
            .map { it.value }
            .sumOf { it.x * 100 + it.y }
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

data class Piece(val symbol: Symbol) {
    override fun equals(other: Any?): Boolean {
        return this === other
    }
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
