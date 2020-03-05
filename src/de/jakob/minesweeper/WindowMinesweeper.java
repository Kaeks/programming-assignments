package de.jakob.minesweeper;

import javax.swing.JFrame;

public class WindowMinesweeper extends JFrame {
    private static final long serialVersionUID = 1L;

    private Minesweeper game;

    private DrawPanel contentPane;

    public WindowMinesweeper() {
        super();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Minesweeper");
        setResizable(false);
        //setUndecorated(true);

        newGame(20, 20, 40);

        contentPane = new DrawPanel(this);
        add(contentPane);
        pack();
        setVisible(true);
    }

    public Minesweeper getGame() {
        return game;
    }

    private void newGame(int width, int height, int amtMines) {
        game = new Minesweeper(width, height, amtMines);
    }

    public static void main(String[] args) {
        WindowMinesweeper frame = new WindowMinesweeper();
        frame.setVisible(true);

    }
}