package de.no3x.adventofcode.twentyfour.day15

class Day15 {

    fun solve(input: String): Int {
        val (pieces, moves) = parseInput(input)
        val board = Board(pieces)
        board.applyMoves(moves)
        return board.calculateBoxCoordinateSum()
    }

    private fun parseInput(input: String): Pair<List<MutableList<Piece>>, MutableList<Move>> {
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
    override fun toString(): String {
        var s = ""
        pieces.forEach {
            it.forEach {
                s += it.symbol.char.toString() + ""
            }
            s += "\n"
        }
        return s
    }

    private fun findRobotPosition(): Position =
        pieces.flatMapIndexed { rowIndex, row ->
            row.mapIndexed { colIndex, piece -> Position(colIndex, rowIndex) to piece }
        }.firstOrNull { (_, piece) -> piece.symbol == Symbol.ROBOT }
            ?.first ?: throw IllegalStateException("No robot found on the board")


    fun applyMoves(moves: List<Move>) {
        moves.forEach { move -> executeMove(move) }
    }

    fun executeMove(move: Move): Boolean {
        val robotPosition = findRobotPosition()
        val moveToExecute = determineAndExecuteMove(robotPosition, move.direction)
        return moveToExecute != null
    }

    private fun determineAndExecuteMove(start: Position, direction: Direction): MoveToExecute? {
        val target = start + direction

        val result = when (getPieceByPosition(target).symbol) {
            Symbol.WALL -> null
            Symbol.EMPTY -> MoveToExecute(start, target)
            Symbol.BOX -> {
                determineAndExecuteMove(target, direction)?.let { // ?. is very important here
                    MoveToExecute(start, target)
                }
            }
            Symbol.ROBOT -> throw IllegalStateException("Position already occupied by single robot")
        }

        result?.let {
            move(it)
        }

        println("Result: $result.")
        return result
    }

    private fun getPieceByPosition(tryPosition: Position): Piece {
        return pieces[tryPosition.column][tryPosition.row]
    }

    private fun move(move: MoveToExecute) {
        move(move.fromPosition, move.toPosition)
    }

    private fun move(
        fromPosition: Position,
        toPosition: Position
    ) {
        println("Try to move from $fromPosition to $toPosition")
        println("Board before: \n$this")
        pieces[toPosition.column][toPosition.row] = pieces[fromPosition.column][fromPosition.row]
        pieces[fromPosition.column][fromPosition.row] = Piece(Symbol.EMPTY)
        println("Board after: \n$this")
    }

    fun calculateBoxCoordinateSum(): Int {
        return pieces.flatMapIndexed { row, piecesOfRow ->
            piecesOfRow.mapIndexed { col, piece ->
                Position(row, col) to piece
            }
        }.filter { (_, piece) ->
            piece.symbol == Symbol.BOX
        }.sumOf { (position, _) ->
            position.row * 100 + position.column
        }
    }
}

class MoveToExecute(
    val fromPosition: Position,
    val toPosition: Position
)

data class Position(val row: Int, val column: Int) {
    operator fun plus(direction: Direction): Position {
        val newVec = Vec2D(row, column) + direction.directionVector
        return Position(newVec.x, newVec.y)
    }
}

data class Vec2D(val x: Int, val y: Int) {
    operator fun plus(other: Vec2D): Vec2D = Vec2D(this.x + other.x, this.y + other.y)
}

data class Piece(val symbol: Symbol)

enum class Symbol(val char: Char) {
    ROBOT('@'),
    BOX('O'),
    EMPTY('.'),
    WALL('#');

    companion object {
        fun of(char: Char): Symbol = entries.find { it.char == char }
            ?: throw IllegalArgumentException("Unknown symbol: $char")
    }
}

data class Move(val direction: Direction) {
}

enum class Direction(private val char: Char, val directionVector: Vec2D) {
    U('^', Vec2D(0, -1)),
    R('>', Vec2D(1, 0)),
    D('v', Vec2D(0, 1)),
    L('<', Vec2D(-1, 0));

    companion object {
        fun of(char: Char): Direction = entries.find { it.char == char }
            ?: throw IllegalArgumentException("Unknown direction: $char")
    }
}
