public class Knight extends Piece {

    public Knight(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        // Rook moves horizontally or vertically
        return this.row == targetRow || this.col == targetCol;
    }

    @Override
    public String getSymbol() {
        return color.equals("W") ? "N" : "n";
    }
}