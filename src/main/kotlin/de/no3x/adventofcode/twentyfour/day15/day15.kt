package de.no3x.adventofcode.twentyfour.day15

class Day15 {

    fun solve(input: String): Int {
        val (pieces, moves) = transformInput(input)
        val board = Board(pieces)
        board.applyMoves(moves)
        return board.sumOfCoordinates()
    }

    private fun transformInput(input: String): Pair<List<MutableList<Piece>>, MutableList<Move>> {
        var separatorHit = false
        val pieces = mutableListOf<MutableList<Piece>>()
        val moves = mutableListOf<Move>()
        input.lines()
            .forEach { line ->
                if (line.isEmpty()) {
                    separatorHit = true
                    return@forEach
                }
                if (!separatorHit) {
                    val piecesOfLine = line.map { Piece(Symbol.of(it)) }.toMutableList()
                    pieces.add(piecesOfLine)
                } else {
                    moves.addAll(line.map {
                        Move(Direction.of(it))
                    })
                }
            }
        return pieces to moves
    }

}

data class Board(val pieces: List<MutableList<Piece>>) {
    val rows: Int get() = pieces.size
    val columns: Int get() = pieces[0].size

    private fun findRobotPosition(): Position {
        return pieces.flatMapIndexed { lineIndex, line ->
                line.mapIndexed { columnIndex, piece ->
                        Position(columnIndex, lineIndex) to piece
                    }
            }
            .find { (_, piece) -> piece.symbol == Symbol.ROBOT }!!
            .first
    }

    fun applyMoves(moves: List<Move>) {
        moves.forEach { move -> tryMoveRobot(move) }
    }

    fun tryMoveRobot(move: Move): Boolean {
        val robotPosition = findRobotPosition()
        val tryMove = getPushableMoves(robotPosition, move.direction)
        return tryMove!=null
    }

    private fun getPushableMoves(
        startPosition: Position,
        direction: Direction
    ): MoveToExecute? {
        val tryPosition = startPosition + direction
        val pieceOnTryPosition = getPieceByPosition(tryPosition)
        println("Try to move to a position with piece of symbol ${pieceOnTryPosition.symbol}.")

        val result: MoveToExecute? = when (pieceOnTryPosition.symbol) {
            Symbol.WALL -> null
            Symbol.EMPTY -> {
                MoveToExecute(startPosition, getPieceByPosition(startPosition), tryPosition, pieceOnTryPosition)
            }
            Symbol.BOX -> {
                val pushableMoves = getPushableMoves(tryPosition, direction)
                if (pushableMoves != null) {
                    MoveToExecute(startPosition, getPieceByPosition(startPosition), tryPosition, pieceOnTryPosition)
                } else {
                    null
                }
            }
            Symbol.ROBOT -> {
                throw IllegalStateException("There is only one robot on the board so no position we try to move to should contain a robot.")
            }
        }

        if (result != null) {
            move(result.fromPosition, result.toPosition)
        }

        println("Result: $result.")
        return result
    }

    private fun getPieceByPosition(tryPosition: Position): Piece {
        return pieces[tryPosition.row][tryPosition.column]
    }

    private fun move(
        fromPosition: Position,
        toPosition: Position
    ) {
        println("Try to move from $fromPosition to $toPosition")
        pieces[toPosition.row][toPosition.column] = pieces[fromPosition.row][fromPosition.column]
        pieces[fromPosition.row][fromPosition.column] = Piece(Symbol.EMPTY)
        println("")
    }

    fun sumOfCoordinates(): Int {
        return pieces.flatMapIndexed { row, piecesOfRow ->
            piecesOfRow.mapIndexed { col, piece ->
                Position(row, col) to piece
            }
        }.filter { (_, piece) ->
            piece.symbol == Symbol.BOX
        }.sumOf {(position, _) ->
            position.row * 100 + position.column
        }
    }
}

class MoveToExecute(  val fromPosition: Position,
                      val fromPiece: Piece,
                      val toPosition: Position,
                      val toPiece: Piece) {

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
    override fun toString(): String {
        return symbol.name
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
