public class Queen extends Piece {

    public Queen(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        // Queen moves horizontally and vertically so Rook + Bishop = Queen
        int rowDiff = Math.abs(targetRow - this.row);
        int colDiff = Math.abs(targetCol - this.col);

        return ((rowDiff > 0 && colDiff == 0) || (colDiff > 0 && rowDiff == 0)) || ((rowDiff == colDiff) && (rowDiff > 0));
    }

    @Override
    public String getSymbol() {
        return color.equals("W") ? "Q" : "q";
    }
}