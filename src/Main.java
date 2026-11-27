public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Chess Game - Board Initialization Test\n");
        Board board = new Board();
        board.printBoard();

        // Test moving a white pawn from e2 to e4
        System.out.println("\nTrying move: e2 e4");
        board.makeMove("e2 e4");
        board.printBoard();

        // Test ilegal move pawn from f2 to f5
        System.out.println("\nTrying move: e2 e4");
        board.makeMove("f2 f5");
        board.printBoard();

        // Test ilegal move rook from h1 to h2
        System.out.println("\nTrying move: h1 h2");
        board.makeMove("h1 h2");
        board.printBoard();

        // Test ilegal move queen from d8 to e1
        System.out.println("\nTrying move: d8 e1");
        board.makeMove("d8 e1");
        board.printBoard();

        // Test e7 to e5
        System.out.println("\nTrying move: e7 e5");
        board.makeMove("e7 e5");
        board.printBoard();

        // Test d2 to d4
        System.out.println("\nTrying move: d2 d4");
        board.makeMove("d2 d4");
        board.printBoard();

        // Test pawn capture e5 d4
        System.out.println("\nTrying move: e5 d4");
        board.makeMove("e5 d4");
        board.printBoard();

        // Test Knight movement g1 f3
        System.out.println("\nTrying move: g1 f3");
        board.makeMove("g1 f3");
        board.printBoard();
    }
}