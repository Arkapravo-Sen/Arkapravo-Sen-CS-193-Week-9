/**
 * Abstract base class representing a generic chess piece on the board.
 * Manages core piece properties such as color, current position, and defines
 * the blueprint for move validation and console symbol representation.
 */
public abstract class Piece {
    protected String color; // "W" for White, "B" for Black
    protected int row;
    protected int col;

    /**
     * Constructs a new Piece with a specified color and starting grid coordinates.
     *
     * @param color The color of the piece ("W" for White, "B" for Black).
     * @param row   The initial row index on the board (0-7).
     * @param col   The initial column index on the board (0-7).
     */
    public Piece(String color, int row, int col) {
        this.color = color;
        this.row = row;
        this.col = col;
    }

    /**
     * Determines whether moving to the specified target coordinates obeys this piece's movement geometry.
     *
     * @param targetRow The destination row index (0-7).
     * @param targetCol The destination column index (0-7).
     * @param board     The current 8x8 board state represented as a 2D Piece array.
     * @return {@code true} if the move geometry is valid for this piece type; {@code false} otherwise.
     */
    public abstract boolean isValidMove(int targetRow, int targetCol, Piece[][] board);
    
    /**
     * Returns the character symbol used for console rendering (uppercase for White, lowercase for Black).
     *
     * @return The string symbol representing the piece.
     */
    public abstract String getSymbol();

    /**
     * Returns the color code of this piece.
     *
     * @return "W" for White or "B" for Black.
     */
    public String getColor() {
        return color;
    }

    /**
     * Updates the internal row and column coordinates of this piece.
     *
     * @param row The new row index (0-7).
     * @param col The new column index (0-7).
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }
}