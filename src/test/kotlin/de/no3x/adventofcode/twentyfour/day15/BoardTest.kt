package de.no3x.adventofcode.twentyfour.day15

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {

    @Test
    fun `cannot move wall`() {
        val pieces = listOf(
            mutableListOf(
                Piece(Symbol.ROBOT),
                Piece(Symbol.WALL),
                Piece(Symbol.EMPTY),
            )
        )
        val board = Board(pieces)

        val result = board.tryMoveRobot(Move(Direction.R))

        assertThat(result).isEqualTo(false)
    }

    @Test
    fun `can push boxes if free`() {
        val pieces = listOf(
            mutableListOf(
                Piece(Symbol.ROBOT),
                Piece(Symbol.BOX),
                Piece(Symbol.BOX),
                Piece(Symbol.BOX),
                Piece(Symbol.EMPTY)
            )
        )
        val board = Board(pieces)

        val result = board.tryMoveRobot(Move(Direction.R))

        assertThat(result).isEqualTo(true)
    }

    @Test
    fun `can not push boxes if not free`() {
        val pieces = listOf(
            mutableListOf(
                Piece(Symbol.ROBOT),
                Piece(Symbol.BOX),
                Piece(Symbol.BOX),
                Piece(Symbol.BOX),
                Piece(Symbol.WALL)
            )
        )
        val board = Board( pieces)

        val result = board.tryMoveRobot(Move(Direction.R))

        assertThat(result).isEqualTo(false)
    }


    @Test
    fun `22`() {
        val pieces = listOf(mutableListOf(
            Piece(Symbol.ROBOT),
            Piece(Symbol.BOX),
            Piece(Symbol.BOX),
            Piece(Symbol.BOX),
            Piece(Symbol.EMPTY)
        ))
        val board = Board(pieces)

        board.applyMoves(listOf(Move(Direction.R)))

        val symbols = board.pieces.flatMap {
            it.map {
                it.symbol
            }
        }
        assertThat(symbols)
            .containsExactly(
                Symbol.EMPTY,
                Symbol.ROBOT,
                Symbol.BOX,
                Symbol.BOX,
                Symbol.BOX
            )
    }

    @Test
    fun `2223`() {
        val pieces = listOf(
            mutableListOf(
                Piece(Symbol.ROBOT),
                Piece(Symbol.BOX),
                Piece(Symbol.BOX),
                Piece(Symbol.BOX),
                Piece(Symbol.EMPTY)
            ),
            mutableListOf(
                Piece(Symbol.BOX),
                Piece(Symbol.BOX),
                Piece(Symbol.BOX),
                Piece(Symbol.BOX),
                Piece(Symbol.EMPTY)
            ),
            mutableListOf(
                Piece(Symbol.EMPTY),
                Piece(Symbol.BOX),
                Piece(Symbol.BOX),
                Piece(Symbol.BOX),
                Piece(Symbol.EMPTY)
            )
        )
        val board = Board(pieces)

        board.applyMoves(listOf(Move(Direction.D)))

        val symbols = board.pieces.flatMap {
            it.map {
                it.symbol
            }
        }
        assertThat(symbols)
            .containsExactly(
                Symbol.EMPTY,
                Symbol.BOX,
                Symbol.BOX,
                Symbol.BOX,
                Symbol.EMPTY,

                Symbol.ROBOT,
                Symbol.BOX,
                Symbol.BOX,
                Symbol.BOX,
                Symbol.EMPTY,


                Symbol.BOX,
                Symbol.BOX,
                Symbol.BOX,
                Symbol.BOX,
                Symbol.EMPTY,
            )
    }

    @Test
    fun `calc gps sum`() {
        val pieces = listOf(
            mutableListOf(
                Piece(Symbol.WALL),
                Piece(Symbol.WALL),
                Piece(Symbol.WALL),
                Piece(Symbol.WALL),
                Piece(Symbol.WALL),
                Piece(Symbol.WALL),
                Piece(Symbol.WALL),
            ),
            mutableListOf(
                Piece(Symbol.WALL),
                Piece(Symbol.EMPTY),
                Piece(Symbol.EMPTY),
                Piece(Symbol.EMPTY),
                Piece(Symbol.BOX),
                Piece(Symbol.EMPTY),
                Piece(Symbol.EMPTY),
            ),
            mutableListOf(
                Piece(Symbol.WALL),
                Piece(Symbol.EMPTY),
                Piece(Symbol.EMPTY),
                Piece(Symbol.EMPTY),
                Piece(Symbol.EMPTY),
                Piece(Symbol.EMPTY),
                Piece(Symbol.EMPTY),
            ),

        )
        val board = Board(pieces)

        val result = board.sumOfCoordinates()

        assertThat(result).isEqualTo(104)
    }
}