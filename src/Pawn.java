public class Pawn extends Piece {

    public Pawn(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        int rowDiff = targetRow - this.row; // Can be negative or positive depending on direction
        int colDiff = Math.abs(targetCol - this.col);
        
        // Determine direction based on color ("W" moves up towards row 0, "B" moves down towards row 7)
        int direction = color.equals("W") ? -1 : 1;
        
        // Forward move (1 square)
        if (colDiff == 0 && rowDiff == direction && board[targetRow][targetCol] == null) {
            return true;
        }
        
        // Initial two-square forward move check if on starting row only then allow for 2 square mvoement
        boolean isStartingRow = (color.equals("W") && this.row == 6) || (color.equals("B") && this.row == 1);
        if (colDiff == 0 && rowDiff == 2 * direction && isStartingRow) {
            return true;
        }
        
        // Diagonal capture
        if (colDiff == 1 && rowDiff == direction && board[targetRow][targetCol] != null) {
            return true;
        }
        
        return false;
    }

    @Override
    public String getSymbol() {
        return color.equals("W") ? "P" : "p";
    }
}