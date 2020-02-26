package de.jakob.minesweeper;

public enum MoveType {

    UNCOVER((byte) 0),
    FLAG((byte) 1, "!"),
    QUESTION((byte) 2, "?");

    private byte value;
    private String inputString;

    private MoveType(byte value, String inputString) {
        this.value = value;
        this.inputString = inputString;
    }

    private MoveType(byte value) {
        this.value = value;
        inputString = "";
    }

    public byte getValue() {
        return value;
    }

    public String getInputString() {
        return inputString;
    }

}
