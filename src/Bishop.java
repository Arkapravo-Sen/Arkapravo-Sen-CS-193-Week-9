public class Bishop extends Piece {

    public Bishop(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        // Bishop moves diagonally so row diff and col diff must be equal
        int rowDiff = Math.abs(targetRow - this.row);
        int colDiff = Math.abs(targetCol - this.col);

        return (rowDiff == colDiff) && (rowDiff > 0);
    }

    @Override
    public String getSymbol() {
        return color.equals("W") ? "B" : "b";
    }
}