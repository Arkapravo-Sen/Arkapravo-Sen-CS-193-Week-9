public class Rook extends Piece {

    public Rook(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        // Rook moves horizontally or vertically so either rowdiff or columndiff can be greater then 0 not both
        int rowDiff = Math.abs(targetRow - this.row);
        int colDiff = Math.abs(targetCol - this.col);

        return (rowDiff > 0 && colDiff == 0) || (colDiff > 0 && rowDiff == 0);
    }

    @Override
    public String getSymbol() {
        return color.equals("W") ? "R" : "r";
    }
}