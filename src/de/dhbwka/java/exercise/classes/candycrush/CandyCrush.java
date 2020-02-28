package de.dhbwka.java.exercise.classes.candycrush;

public class CandyCrush {
    
    private Field field;
    // private int msTime;
    private Player player;
    
    // private final int MS_TIME_INCREMENT = 45000;

    public CandyCrush(int size, Player player, byte colors) {
        field = new Field(size, colors);
        this.player = player;
    }

    public CandyCrush(int size, Player player) {
        field = new Field(size, 7);
        this.player = player;
    }

    public CandyCrush(Player player) {
        field = new Field(9, 7);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Field getField() {
        return field;
    }

    public void move(Move move) {
        field.commit(move);
    }

}