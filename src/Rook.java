public class Rook extends Piece {

    public Rook(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        // Rook moves horizontally or vertically
        return this.row == targetRow || this.col == targetCol;
    }

    @Override
    public String getSymbol() {
        return color.equals("W") ? "R" : "r";
    }
}