package de.dhbwka.java.exercise.classes;

import java.util.Locale;

public class Radio {
    private boolean on;
    private int volume;
    private double frequency;

    public Radio() {
        on = true;
        volume = 50;
        frequency = 85.0;
    }

    public Radio(boolean on, int volume, double frequency) {
        this.on = on;
        this.volume = volume;
        this.frequency = frequency;
    }

    boolean getOn() { return on; }
    int getVolume() { return volume; }
    double getFrequency() { return frequency; }

    void incVolume() {
        if (!on) return;
        volume = volume < 100 ? volume + 1 : 100;
    }

    void decVolume() {
        if (!on) return;
        volume = volume > 0 ? volume - 1 : 0;
    }

    void turnOn() { on = true; }
    void turnOff() { on = false; }

    void setFrequency(double frequency) {
        this.frequency = frequency <= 110.0 && frequency >= 85.0 ? frequency : 99.9d;
    }

    @Override
    public String toString() {
        String onString = on ? "an " : "aus";
        return String.format(Locale.US, "Radio %s: Frequency = %3.1f MHz Volume = %3d", onString, frequency, volume);
    }
}
