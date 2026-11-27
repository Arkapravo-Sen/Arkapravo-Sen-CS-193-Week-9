import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        
        boolean isWhiteTurn = true;
        boolean gameRunning = true;

        System.out.println("=================================");
        System.out.println("   WELCOME TO TERMINAL CHESS!    ");
        System.out.println("=================================");
        System.out.println("Enter moves in standard notation (e.g., e2 e4). Type 'exit' to quit.\n");

        while (gameRunning) {
            board.printBoard();
            
            String currentPlayer = isWhiteTurn ? "W" : "B";
            String playerName = isWhiteTurn ? "White (W)" : "Black (B)";
            
            System.out.println("\nTurn: " + playerName);
            System.out.print("Enter your move: ");
            
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("exit")) {
                gameRunning = false;
                System.out.println("Game exited.");
                break;
            }
            
            // Execute the move
            boolean success = board.makeMove(input, currentPlayer);
            
            if (success) {
                // 1. Switch turns
                isWhiteTurn = !isWhiteTurn;
                String nextPlayer = isWhiteTurn ? "W" : "B";
                String nextPlayerName = isWhiteTurn ? "White" : "Black";
                
                // 2. Check the status of the player whose turn is next
                if (board.isCheckmate(nextPlayer)) {
                    board.printBoard();
                    String winner = isWhiteTurn ? "Black" : "White";
                    System.out.println("\n=================================");
                    System.out.println("   CHECKMATE! " + winner + " wins!   ");
                    System.out.println("=================================");
                    gameRunning = false;
                } else if (board.isStalemate(nextPlayer)) {
                    board.printBoard();
                    System.out.println("\n=================================");
                    System.out.println("   STALEMATE! Game is a draw.    ");
                    System.out.println("=================================");
                    gameRunning = false;
                } else if (board.isKingInCheck(nextPlayer)) {
                    System.out.println("\n>> WARNING: " + nextPlayerName + " is in CHECK!");
                }
            } else {
                System.out.println(">> Move failed. Please try again.");
            }
            
            System.out.println();
        }
        
        scanner.close();
    }
}