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
            System.out.println("\nTurn: " + currentPlayer);
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
                // Switch turns if the move was valid
                isWhiteTurn = !isWhiteTurn;
            } else {
                System.out.println(">> Move failed. Please try again.");
            }
            
            System.out.println();
        }
        
        scanner.close();
    }
}