/**
 * Represents a King chess piece.
 * Inherits from the abstract {@link Piece} class and implements single-square
 * movement validation rules in any direction (horizontally, vertically, or diagonally).
 */
public class King extends Piece {

    /**
     * Constructs a new King with the specified color and initial coordinates.
     *
     * @param color The color of the king ("W" for White, "B" for Black).
     * @param row   The initial row index on the board (0-7).
     * @param col   The initial column index on the board (0-7).
     */
    public King(String color, int row, int col) {
        super(color, row, col);
    }

    /**
     * Validates whether moving to the specified target coordinates is a legal move for a King.
     * The King may move exactly one square in any direction (horizontally, vertically, or diagonally).
     *
     * @param targetRow The destination row index (0-7).
     * @param targetCol The destination column index (0-7).
     * @param board     The current 8x8 board state represented as a 2D Piece array.
     * @return {@code true} if the move is within one square in any direction; {@code false} otherwise.
     */
    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        // King moves horizontally vertically diagonally one square max
        int rowDiff = Math.abs(targetRow - this.row);
        int colDiff = Math.abs(targetCol - this.col);

        return (rowDiff <= 1 && colDiff <= 1) && (rowDiff > 0 || colDiff > 0);
    }

    /**
     * Returns the console symbol for the king ("K" for White, "k" for Black).
     *
     * @return The string symbol representing the king.
     */
    @Override
    public String getSymbol() {
        return color.equals("W") ? "K" : "k";
    }
}