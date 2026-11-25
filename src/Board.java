public class Board {
    private String[][] grid;

    public Board() {
        grid = new String[8][8];
        setupInitialDisplay();
    }

    // Fills the grid with placeholder symbols for an initial visual test
    public void setupInitialDisplay() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                grid[r][c] = ".";
            }
        }
        
        // Place some placeholder pieces to test visual output
        grid[0][0] = "r"; // Black Rook
        grid[0][7] = "r"; // Black Rook
        grid[7][0] = "R"; // White Rook
        grid[7][7] = "R"; // White Rook
        grid[7][4] = "K"; // White King
        grid[0][4] = "k"; // Black King
    }

    // Prints the 8x8 grid with file (a-h) and rank (1-8) labels
    public void printBoard() {
        System.out.println("    a   b   c   d   e   f   g   h");
        System.out.println("  ---------------------------------");
        
        for (int r = 0; r < 8; r++) {
            // Print rank number (8 down to 1)
            System.out.print((8 - r) + " |");
            
            for (int c = 0; c < 8; c++) {
                System.out.print(" " + grid[r][c] + " |");
            }
            
            System.out.println(" " + (8 - r));
            System.out.println("  ---------------------------------");
        }
        
        System.out.println("    a   b   c   d   e   f   g   h");
    }
}