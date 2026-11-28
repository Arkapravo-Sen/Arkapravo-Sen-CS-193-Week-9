/**
 * Represents a Knight chess piece.
 * Inherits from the abstract {@link Piece} class and implements L-shaped
 * movement validation rules (jumping capability included).
 */
public class Knight extends Piece {

    /**
     * Constructs a new Knight with the specified color and initial coordinates.
     *
     * @param color The color of the knight ("W" for White, "B" for Black).
     * @param row   The initial row index on the board (0-7).
     * @param col   The initial column index on the board (0-7).
     */
    public Knight(String color, int row, int col) {
        super(color, row, col);
    }

    /**
     * Validates whether moving to the specified target coordinates is a legal L-shaped move for a Knight.
     * A valid move consists of moving 2 squares in one orthogonal direction and 1 square
     * in the perpendicular direction.
     *
     * @param targetRow The destination row index (0-7).
     * @param targetCol The destination column index (0-7).
     * @param board     The current 8x8 board state represented as a 2D Piece array.
     * @return {@code true} if the move forms a valid L-shape; {@code false} otherwise.
     */
    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        // Knight moves in an L shape
        int rowDiff = Math.abs(targetRow - this.row);
        int colDiff = Math.abs(targetCol - this.col);

        // Valid L shape
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }

    /**
     * Returns the console symbol for the knight ("N" for White, "n" for Black).
     *
     * @return The string symbol representing the knight.
     */
    @Override
    public String getSymbol() {
        return color.equals("W") ? "N" : "n";
    }
}