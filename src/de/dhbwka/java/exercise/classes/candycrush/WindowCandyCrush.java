package de.dhbwka.java.exercise.classes.candycrush;

import javax.swing.JFrame;

public class WindowCandyCrush extends JFrame {
	private static final long serialVersionUID = 1L;

    private CandyCrush game;

    private DrawPanel contentPane;

	public WindowCandyCrush() {
        super();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("CandyCrush");
        setResizable(false);

        newGame(new Player("TestPlayerName"));

        contentPane = new DrawPanel(this);
        add(contentPane);
        pack();
        setVisible(true);
    }

    private void newGame(Player player) {
        this.game = new CandyCrush(player);
    }

    public CandyCrush getGame() {
        return game;
    }

}