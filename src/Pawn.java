/**
 * Represents a Pawn chess piece.
 * Inherits from the abstract {@link Piece} class and implements specialized movement rules
 * including single-square forward steps, two-square initial advances, and diagonal captures.
 */
public class Pawn extends Piece {

    /**
     * Constructs a new Pawn with the specified color and initial coordinates.
     *
     * @param color The color of the pawn ("W" for White, "B" for Black).
     * @param row   The initial row index on the board (0-7).
     * @param col   The initial column index on the board (0-7).
     */
    public Pawn(String color, int row, int col) {
        super(color, row, col);
    }

    /**
     * Validates whether moving to the specified target coordinates is a legal move for a Pawn.
     * Enforces directionality (White moves up/decreasing rows, Black moves down/increasing rows),
     * single-square forward advancement, initial two-square advancement (with obstruction checks),
     * and diagonal enemy piece captures.
     *
     * @param targetRow The destination row index (0-7).
     * @param targetCol The destination column index (0-7).
     * @param board     The current 8x8 board state represented as a 2D Piece array.
     * @return {@code true} if the move is valid for a pawn; {@code false} otherwise.
     */
    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        int rowDiff = targetRow - this.row;
        int colDiff = Math.abs(targetCol - this.col);

        // White moves up, Black moves down
        int direction = color.equals("W") ? -1 : 1;

        // One square forward: destination must be empty
        if (colDiff == 0 && rowDiff == direction) {
            return board[targetRow][targetCol] == null;
        }

        // Two squares forward: must be on starting row,
        // destination AND intermediate square must be empty
        boolean isStartingRow =
                (color.equals("W") && this.row == 6) ||
                (color.equals("B") && this.row == 1);

        if (colDiff == 0 && rowDiff == 2 * direction && isStartingRow) {

            int middleRow = this.row + direction;

            return board[middleRow][this.col] == null &&
                board[targetRow][targetCol] == null;
        }

        // Diagonal capture: must capture an enemy piece
        if (colDiff == 1 && rowDiff == direction) {

            Piece targetPiece = board[targetRow][targetCol];

            return targetPiece != null &&
                !targetPiece.getColor().equals(this.color);
        }

        return false;
    }

    /**
     * Returns the console symbol for the pawn ("P" for White, "p" for Black).
     *
     * @return The string symbol representing the pawn.
     */
    @Override
    public String getSymbol() {
        return color.equals("W") ? "P" : "p";
    }
}