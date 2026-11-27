public class King extends Piece {

    public King(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        // King moves horizontally vertically diagonally one square max
        int rowDiff = Math.abs(targetRow - this.row);
        int colDiff = Math.abs(targetCol - this.col);

        return (rowDiff <= 1 && colDiff <= 1) && (rowDiff > 0 || colDiff > 0);
    }

    @Override
    public String getSymbol() {
        return color.equals("W") ? "K" : "k";
    }
}