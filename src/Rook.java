/**
 * Represents a Rook chess piece.
 * Inherits from the abstract {@link Piece} class and implements horizontal and vertical
 * movement validation rules across any number of unobstructed squares.
 */
public class Rook extends Piece {

    /**
     * Constructs a new Rook with the specified color and initial coordinates.
     *
     * @param color The color of the rook ("W" for White, "B" for Black).
     * @param row   The initial row index on the board (0-7).
     * @param col   The initial column index on the board (0-7).
     */
    public Rook(String color, int row, int col) {
        super(color, row, col);
    }

    /**
     * Validates whether moving to the specified target coordinates is a legal move for a Rook.
     * The Rook moves horizontally or vertically, meaning either the row difference or column
     * difference can be greater than zero, but not both simultaneously.
     *
     * @param targetRow The destination row index (0-7).
     * @param targetCol The destination column index (0-7).
     * @param board     The current 8x8 board state represented as a 2D Piece array.
     * @return {@code true} if the move forms a valid straight rank or file line; {@code false} otherwise.
     */
    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        // Rook moves horizontally or vertically so either rowdiff or columndiff can be greater than 0 not both
        int rowDiff = Math.abs(targetRow - this.row);
        int colDiff = Math.abs(targetCol - this.col);

        return (rowDiff > 0 && colDiff == 0) || (colDiff > 0 && rowDiff == 0);
    }

    /**
     * Returns the console symbol for the rook ("R" for White, "r" for Black).
     *
     * @return The string symbol representing the rook.
     */
    @Override
    public String getSymbol() {
        return color.equals("W") ? "R" : "r";
    }
}