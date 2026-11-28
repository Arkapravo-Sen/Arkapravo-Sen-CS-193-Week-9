/**
 * Represents a Bishop chess piece.
 * Inherits from the abstract {@link Piece} class and implements diagonal movement validation rules.
 */
public class Bishop extends Piece {

    /**
     * Constructs a new Bishop with the specified color and initial coordinates.
     *
     * @param color The color of the bishop ("W" for White, "B" for Black).
     * @param row   The initial row index on the board (0-7).
     * @param col   The initial column index on the board (0-7).
     */
    public Bishop(String color, int row, int col) {
        super(color, row, col);
    }

    /**
     * Validates whether moving to the specified target coordinates is a legal diagonal move.
     * The absolute row difference must equal the absolute column difference, and the move
     * must cover a distance greater than zero squares.
     *
     * @param targetRow The destination row index (0-7).
     * @param targetCol The destination column index (0-7).
     * @param board     The current 8x8 board state represented as a 2D Piece array.
     * @return {@code true} if the move is a valid diagonal; {@code false} otherwise.
     */
    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        // Bishop moves diagonally so row diff and col diff must be equal
        int rowDiff = Math.abs(targetRow - this.row);
        int colDiff = Math.abs(targetCol - this.col);

        return (rowDiff == colDiff) && (rowDiff > 0);
    }

    /**
     * Returns the console symbol for the bishop ("B" for White, "b" for Black).
     *
     * @return The string symbol representing the bishop.
     */
    @Override
    public String getSymbol() {
        return color.equals("W") ? "B" : "b";
    }
}