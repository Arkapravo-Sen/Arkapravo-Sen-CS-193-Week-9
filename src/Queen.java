/**
 * Represents a Queen chess piece.
 * Inherits from the abstract {@link Piece} class and implements combined movement validation
 * rules of both the Rook and the Bishop (moving horizontally, vertically, or diagonally).
 */
public class Queen extends Piece {

    /**
     * Constructs a new Queen with the specified color and initial coordinates.
     *
     * @param color The color of the queen ("W" for White, "B" for Black).
     * @param row   The initial row index on the board (0-7).
     * @param col   The initial column index on the board (0-7).
     */
    public Queen(String color, int row, int col) {
        super(color, row, col);
    }

    /**
     * Validates whether moving to the specified target coordinates is a legal move for a Queen.
     * The Queen may move any number of unobstructed squares horizontally, vertically, or diagonally.
     *
     * @param targetRow The destination row index (0-7).
     * @param targetCol The destination column index (0-7).
     * @param board     The current 8x8 board state represented as a 2D Piece array.
     * @return {@code true} if the move forms a valid straight line or diagonal; {@code false} otherwise.
     */
    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        // Queen moves horizontally and vertically so Rook + Bishop = Queen
        int rowDiff = Math.abs(targetRow - this.row);
        int colDiff = Math.abs(targetCol - this.col);

        return ((rowDiff > 0 && colDiff == 0) || (colDiff > 0 && rowDiff == 0)) || ((rowDiff == colDiff) && (rowDiff > 0));
    }

    /**
     * Returns the console symbol for the queen ("Q" for White, "q" for Black).
     *
     * @return The string symbol representing the queen.
     */
    @Override
    public String getSymbol() {
        return color.equals("W") ? "Q" : "q";
    }
}