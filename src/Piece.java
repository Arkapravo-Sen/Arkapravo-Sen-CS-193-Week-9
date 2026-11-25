public abstract class Piece {
    protected String color; // "W" for White, "B" for Black
    protected int row;
    protected int col;

    public Piece(String color, int row, int col) {
        this.color = color;
        this.row = row;
        this.col = col;
    }

    // Abstract method for move validation (to be fully implemented in Commit 4)
    public abstract boolean isValidMove(int targetRow, int targetCol, Piece[][] board);
    
    // Returns the letter symbol for terminal display (uppercase for White, lowercase for Black)
    public abstract String getSymbol();

    public String getColor() {
        return color;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }
}