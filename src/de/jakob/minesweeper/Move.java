package de.jakob.minesweeper;

public class Move {

    private Position position;
    private MoveType moveType;

    public Move(Position position, MoveType moveType) {
        this.position = position;
        this.moveType = moveType;
    }

    public Position getPosition() {
        return position;
    }

    public MoveType getMoveType() {
        return moveType;
    }

}