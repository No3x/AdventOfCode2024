package de.no3x.adventofcode.twentyfour.day15

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.Comparator

class BoardTest {

    @Test
    fun `can push boxes if free`() {
        val pieces = listOf(
            Piece(Symbol.ROBOT),
            Piece(Symbol.BOX),
            Piece(Symbol.BOX),
            Piece(Symbol.BOX),
            Piece(Symbol.EMPTY)
        )
        val board = Board(8, 8, pieces)

        val result = board.tryMoveRobot(Move(Direction.R))

        assertThat(result).isEqualTo(true)
    }

    @Test
    fun `can not push boxes if not free`() {
        val pieces = listOf(
            Piece(Symbol.ROBOT),
            Piece(Symbol.BOX),
            Piece(Symbol.BOX),
            Piece(Symbol.BOX),
            Piece(Symbol.WALL)
        )
        val board = Board(8, 8, pieces)

        val result = board.tryMoveRobot(Move(Direction.R))

        assertThat(result).isEqualTo(false)
    }


    @Test
    fun `22`() {
        val pieces = listOf(
            Piece(Symbol.ROBOT),
            Piece(Symbol.BOX),
            Piece(Symbol.BOX),
            Piece(Symbol.BOX),
            Piece(Symbol.EMPTY)
        )
        val board = Board(1, 5, pieces)

        board.applyMoves(listOf(Move(Direction.R)))

        val positions = board.positions
        val sortedWith = positions.entries
            .sortedWith(compareBy({ it.value.x }, { it.value.y }))
            .map { it.key.symbol }

        assertThat(sortedWith)
            .containsExactly(Symbol.EMPTY, Symbol.ROBOT, Symbol.BOX,
                    Symbol.BOX,
                    Symbol.BOX)
    }
}