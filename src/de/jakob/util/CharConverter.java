package de.jakob.util;

import java.util.IllegalFormatConversionException;
import java.util.IllegalFormatException;

public class CharConverter {

    public CharConverter() {

    }

    public char getLcIfLetter(char c) {
        if (Character.isLetter(c)) {
            return Character.isLowerCase(c) ? c : Character.toUpperCase(c);
        }
        throw new IllegalArgumentException(String.format("Character (%s) is not a letter.", c));
    }

    public char getUcIfLetter(char c) {
        if (Character.isLetter(c)) {
            return Character.isUpperCase(c) ? c : Character.toLowerCase(c);
        }
        throw new IllegalArgumentException(String.format("Character (%s) is not a letter.", c));
    }

}
