package de.jakob.minesweeper;

public class Cell {

    private Position position;
    private boolean mine;
    private boolean flag;
    private boolean question;
    private boolean covered = true;

    public Cell(Position position) {
        this.position = position;
    }

    public Position getPosition() { return position; }

    public boolean isMine() { return mine; }
    public boolean isFlag() { return flag; }
    public boolean isQuestion() { return question; }
    public boolean isCovered() { return covered; }

    public boolean uncover() {
        if (!covered) return false;
        covered = false;
        return true;
    }

    public boolean setMine() {
        if (mine) return false;
        mine = true;
        return true;
    }

    public boolean toggleFlag() {
        flag = isCovered() ? !flag : false;
        if (flag) question = false;
        return flag;
    }

    public boolean toggleQuestion() {
        question = isCovered() ? !question : false;
        if (question) flag = false;
        return question;
    }

    @Override
    public String toString() {
        return String.format(
            "- Cell -%n%s%nC: %s, M: %s, F: %s, Q: %s",
            position,
            covered,
            mine,
            flag,
            question
        );
    }

}