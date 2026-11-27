public class Pawn extends Piece {

    public Pawn(String color, int row, int col) {
        super(color, row, col);
    }

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

    @Override
    public String getSymbol() {
        return color.equals("W") ? "P" : "p";
    }
}