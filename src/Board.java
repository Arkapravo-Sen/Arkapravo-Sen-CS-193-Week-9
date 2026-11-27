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
            grid[6][i] = new Pawn("W", 6, i);
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

    public boolean isPathClear(int startRow, int startCol, int targetRow, int targetCol) {
        int rowStep = Integer.compare(targetRow, startRow);
        int colStep = Integer.compare(targetCol, startCol);

        int currRow = startRow + rowStep;
        int currCol = startCol + colStep;

        // If the path has a piece return false
        while (currRow != targetRow || currCol != targetCol) {
            if (grid[currRow][currCol] != null) {
                return false;
            }
            currRow += rowStep;
            currCol += colStep;
        }

        return true;
    }

    public boolean isLegalMove(int startRow, int startCol, int targetRow, int targetCol) {
        // Checks if within bounds
        if (targetRow < 0 || targetRow > 7 || targetCol < 0 || targetCol > 7) {
            return false;
        }
    
        Piece piece = grid[startRow][startCol];
        if (piece == null) return false;

        Piece targetPiece = grid[targetRow][targetCol];
        
        // No friendly fire
        if (targetPiece != null && targetPiece.getColor().equals(piece.getColor())) {
            return false;
        }

        // Check validity of moves
        if (!(piece.isValidMove(targetRow, targetCol, grid))) {
            return false;
        }

        // Check path for everything but the knight
        if (!(piece instanceof Knight)) {
            if (!(isPathClear(startRow, startCol, targetRow, targetCol))) {
                return false;
            }
        }

        return true;
    }

    // Parses chess notation like "e2" into array indices [row, col].
    public int[] parseCoordinate(String coord) {
        if (coord == null || coord.length() != 2) return null;
        
        char file = coord.charAt(0); // 'a' through 'h'
        char rank = coord.charAt(1); // '1' through '8'
        
        int col = file - 'a';
        int row = 8 - Character.getNumericValue(rank);
        
        // Validate bounds
        if (col < 0 || col > 7 || row < 0 || row > 7) {
            return null;
        }
        
        return new int[] { row, col };
    }

    // Takes a string input (like "e2 e4"), validates it, and executes the move
    public boolean makeMove(String moveInput) {
        // Clean up input format (removes all beginning, trailing, and in-between whitespaces)
        moveInput = moveInput.trim().replaceAll("\\s+", "");
        if (moveInput.length() != 4) {
            System.out.println("Invalid format. Use chess notation like 'e2 e4'.");
            return false;
        }
        
        String startStr = moveInput.substring(0, 2);
        String targetStr = moveInput.substring(2, 4);
        
        int[] start = parseCoordinate(startStr);
        int[] target = parseCoordinate(targetStr);
        
        if (start == null || target == null) {
            System.out.println("Invalid coordinates!");
            return false;
        }
        
        int startRow = start[0], startCol = start[1];
        int targetRow = target[0], targetCol = target[1];
        
        // Check if move is legal using your built-up rules
        if (!isLegalMove(startRow, startCol, targetRow, targetCol)) {
            System.out.println("Illegal move! Try again.");
            return false;
        }
        
        // Execute the move: Update piece position and move it on the grid
        Piece piece = grid[startRow][startCol];
        piece.setPosition(targetRow, targetCol);
        grid[targetRow][targetCol] = piece;
        grid[startRow][startCol] = null;
        
        return true;
    }
}