public class Board {
    private Piece[][] grid;

    public Board() {
        grid = new Piece[8][8];
        setupInitialDisplay();
    }

    // Fills the grid with placeholder symbols for an initial visual test
    public void setupInitialDisplay() {

        // Black Pieces
        grid[0][0] = new Rook("B", 0, 0);
        grid[0][1] = new Knight("B", 0, 1);
        grid[0][2] = new Bishop("B", 0, 2);
        grid[0][3] = new Queen("B", 0, 3);
        grid[0][4] = new King("B", 0, 4);
        grid[0][5] = new Bishop("B", 0, 5);
        grid[0][6] = new Knight("B", 0, 6);
        grid[0][7] = new Rook("B", 0, 7);
        
        for (int i = 0; i < 8; i++) {
            grid[1][i] = new Pawn("B", 1, i);
        }

        // White Pieces
        grid[7][0] = new Rook("W", 7, 0);
        grid[7][1] = new Knight("W", 7, 1);
        grid[7][2] = new Bishop("W", 7, 2);
        grid[7][3] = new Queen("W", 7, 3);
        grid[7][4] = new King("W", 7, 4);
        grid[7][5] = new Bishop("W", 7, 5);
        grid[7][6] = new Knight("W", 7, 6);
        grid[7][7] = new Rook("W", 7, 7);
        
        for (int i = 0; i < 8; i++) {
            grid[6][i] = new Pawn("B", 6, i);
        }
    }

    // Prints the 8x8 grid with file (a-h) and rank (1-8) labels
    public void printBoard() {
        System.out.println("    a   b   c   d   e   f   g   h");
        System.out.println("  ---------------------------------");
        
        for (int r = 0; r < 8; r++) {
            // Print rank number (8 down to 1)
            System.out.print((8 - r) + " |");

            for (int c = 0; c < 8; c++) {
                String symbol = (grid[r][c] == null) ? "." : grid[r][c].getSymbol();
                System.out.print(" " + symbol + " |");
            }
            
            System.out.println(" " + (8 - r));
            System.out.println("  ---------------------------------");
        }
        
        System.out.println("    a   b   c   d   e   f   g   h");
    }
}