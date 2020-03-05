package de.jakob.minesweeper;

public class Minesweeper {

    private int amtMines;
    private Field field;
    private int moves;
    private PlayingStatus status;

    public Minesweeper(int width, int height, int amtMines) {
        if (width * height <= amtMines) {
            throw new IllegalArgumentException(String.format("Amount of mines (%d) must be less than amount of cells in the field (%d).", amtMines, width * height));
        }
        field = new Field(width, height);
        this.amtMines = amtMines;
        status = PlayingStatus.BEFORE;
    }

    public Field getField() {
        return field;
    }

    public int getAmtMines() {
        return amtMines;
    }

    public int getMoves() {
        return moves;
    }

    public PlayingStatus getStatus() {
        return status;
    }

    private boolean checkWon() {
        return field.allMinesFound();
    }

    private void start(Position position) {
        field.fillAround(position, amtMines);
        status = PlayingStatus.PLAYING;
    }

    private void win() {
        status = PlayingStatus.WON;
    }

    private void lose() {
        status = PlayingStatus.LOST;
    }

    public void move(Move move) {
        if (status == PlayingStatus.BEFORE) start(move.getPosition());
        if (status != PlayingStatus.PLAYING) return;
        Cell cell = field.commit(move);
        if (move.getMoveType() == MoveType.UNCOVER) {
            if (checkWon()) win();
            if (cell.isMine()) lose();
        }
    }
}