package finalproject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static finalproject.ChessSudoku.readInteger;

public class Visualizer extends Thread {

    final Dimension BOARD_PANEL_SIZE = new Dimension(800, 800);
    final Dimension GAME_FRAME_SIZE = new Dimension(900, 900);
    JFrame gameFrame;
    BoardPanel boardPanel;
    ChessSudoku game;
    JButton refreshButton;
    ArrayList<Square> lastHighlighted = new ArrayList<>();

    public Visualizer(ChessSudoku game){
        this.gameFrame = new JFrame("Chess Sudoku Visualizer");
        this.game = game;
        this.boardPanel = new BoardPanel();
        this.gameFrame.add(boardPanel, BorderLayout.CENTER);
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameFrame.setSize(GAME_FRAME_SIZE);
        this.refreshButton = new JButton("Update the grid");
        this.refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardPanel.refreshBoard();
                gameFrame.validate();
            }
        });
        this.gameFrame.add(refreshButton, BorderLayout.SOUTH);
        this.gameFrame.setVisible(true);
        this.gameFrame.validate();
    }

    public class BoardPanel extends JPanel {

        ArrayList<Square> boardSquares = new ArrayList<Square>();

        public BoardPanel() {

            super(new GridLayout(game.N, game.N));

            for (int i = 0; i < game.N; i++) {
                for (int j = 0; j < game.N; j++) {
                    Square square = new Square(i, j, game.grid[i][j]);
                    boardSquares.add(square);
                    add(square);
                }
            }
            setPreferredSize(BOARD_PANEL_SIZE);
            validate();
        }

        public Square getSquare(int x, int y) {
            for (Square square : boardSquares) {
                if (square.x == x && square.y == y) return square;
            }
            return null;
        }

        public void refreshBoard() {
            for(Square square : lastHighlighted){
                square.setBackground(Color.WHITE);
            }
            for (Square square : boardSquares) {
                square.refreshSquare();
                validate();
            }
        }
    }

    public class Square extends JPanel{

        int x;
        int y;
        int number;

        public Square(int x, int y, int number){
            super(new GridBagLayout());
            this.x = x;
            this.y = y;
            this.number = game.grid[x][y];
            if(number != 0) add(new JLabel("" + number));
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            validate();
        }

        public void refreshSquare(){
            if(number != game.grid[x][y]) {
                this.removeAll();
                this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                number = game.grid[x][y];
                add(new JLabel("" + number));
                validate();
                setBackground(Color.GREEN);
                lastHighlighted.add(this);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        InputStream in = new FileInputStream("veryEasy3x3.txt");

        // The first number in all Sudoku files must represent the size of the puzzle.  See
        // the example files for the file format.
        int puzzleSize = readInteger( in );
        if( puzzleSize > 100 || puzzleSize < 1 ) {
            System.out.println("Error: The Sudoku puzzle size must be between 1 and 100.");
            System.exit(-1);
        }

        ChessSudoku s = new ChessSudoku( puzzleSize );

        // You can modify these to add rules to your sudoku
        s.knightRule = false;
        s.kingRule = false;
        s.queenRule = false;

        // read the rest of the Sudoku puzzle
        s.read( in );

        //Visualize
        Visualizer visualizer = new Visualizer(s);

        // Solve the puzzle by finding one solution.
        s.solve(false);

    }
}
