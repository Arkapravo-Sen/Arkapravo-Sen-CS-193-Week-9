import java.util.Scanner;

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
    public boolean makeMove(String moveInput, String currentPlayer) {
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

        // ----------------------------------------
        // See if move leaves / moves king in check
        // ----------------------------------------

        // Save current state of board
        Piece piece = grid[startRow][startCol];
        Piece capturedPiece = grid[targetRow][targetCol]; // if there is a captured piece save it. could be null also

        // Make the move temporarily on the grid
        grid[startRow][startCol] = null;
        grid[targetRow][targetCol] = piece;
        piece.setPosition(targetRow, targetCol);

        // Test if king is in check
        boolean kingIsInCheck = isKingInCheck(currentPlayer);

        // Revert the state of the board to before
        grid[targetRow][targetCol] = capturedPiece; // Put back any captured piece (or null)
        grid[startRow][startCol] = piece;           // Put the moving piece back
        piece.setPosition(startRow, startCol);      // Restore piece's internal coordinates

        // If the king was in check return false otherwise move on
        if (kingIsInCheck) {
            System.out.println("Illegal move! Leaves or puts your king in check.");
            return false;
        }

        // Execute the move: Update piece position and move it on the grid
        piece = grid[startRow][startCol];
        piece.setPosition(targetRow, targetCol);
        grid[targetRow][targetCol] = piece;
        grid[startRow][startCol] = null;

        // Check for Pawn Promotion
        if (piece instanceof Pawn) {
            if ((piece.getColor().equals("W") && targetRow == 0) ||
                (piece.getColor().equals("B") && targetRow == 7)) {
                promotePawn(targetRow, targetCol, piece.getColor());
            }
        }
        
        return true;
    }

    // The following blocks will be used to see if a king is in check

    // Find position of a king based on piece color
    public int[] findKing(String color) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c];
                if (p instanceof King && p.getColor().equals(color)) {
                    return new int[] { r, c };
                }
            }
        }
        return null; // Should never happen in a normal game!
    }

    // Can an enemy piece attack a certain square? (Used to find checks)
    public boolean isSquareUnderAttack(int targetRow, int targetCol, String defendingColor) {
        String attackingColor = defendingColor.equals("W") ? "B" : "W";
        
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c];
                // If we find an enemy piece, check if it can legally attack the target square
                if (p != null && p.getColor().equals(attackingColor)) {
                    // Note: We use raw isValidMove/isPathClear here, bypassing standard turn checks
                    if (p.isValidMove(targetRow, targetCol, grid)) {
                        if (p instanceof Knight || isPathClear(r, c, targetRow, targetCol)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Used to see if the king is in check based on color
    public boolean isKingInCheck(String color) {
        int[] kingPos = findKing(color);
        if (kingPos == null) return false;
        return isSquareUnderAttack(kingPos[0], kingPos[1], color);
    }

    public boolean hasAnyLegalMoves(String color) {
        for (int startRow = 0; startRow < 8; startRow++) {
            for (int startCol = 0; startCol < 8; startCol++) {
                Piece piece = grid[startRow][startCol];
                
                // Find every piece belonging to this player
                if (piece != null && piece.getColor().equals(color)) {
                    // Test every possible destination square on the board
                    for (int targetRow = 0; targetRow < 8; targetRow++) {
                        for (int targetCol = 0; targetCol < 8; targetCol++) {
                            if (isLegalMove(startRow, startCol, targetRow, targetCol)) {
                                // Simulate the move
                                Piece capturedPiece = grid[targetRow][targetCol];
                                grid[startRow][startCol] = null;
                                grid[targetRow][targetCol] = piece;
                                piece.setPosition(targetRow, targetCol);

                                boolean stillInCheck = isKingInCheck(color);

                                // Revert the move
                                grid[targetRow][targetCol] = capturedPiece;
                                grid[startRow][startCol] = piece;
                                piece.setPosition(startRow, startCol);

                                // Found at least one valid escape!
                                if (!stillInCheck) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false; // No legal moves exist
    }

    // Checkmate condition, in check and no legal moves
    public boolean isCheckmate(String color) {
        return isKingInCheck(color) && !hasAnyLegalMoves(color);
    }

    // Stalemate condition, no legal moves, not in check
    public boolean isStalemate(String color) {
        return !isKingInCheck(color) && !hasAnyLegalMoves(color);
    }

    // Method for pawn promotion
    public void promotePawn(int row, int col, String color) {
        Scanner scanner = new Scanner(System.in);
        Piece promotedPiece = null;

        while (promotedPiece == null) {
            System.out.print("Pawn promotion! Choose (Q)ueen, (R)ook, (B)ishop, or (K)night: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "Q":
                    promotedPiece = new Queen(color, row, col);
                    break;
                case "R":
                    promotedPiece = new Rook(color, row, col);
                    break;
                case "B":
                    promotedPiece = new Bishop(color, row, col);
                    break;
                case "K":
                    promotedPiece = new Knight(color, row, col);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter Q, R, B, or K.");
            }
        }

        grid[row][col] = promotedPiece;
        System.out.println("Pawn promoted to " + promotedPiece.getSymbol() + "!");
        scanner.close();
    }
}