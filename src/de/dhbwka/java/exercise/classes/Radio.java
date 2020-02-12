package de.dhbwka.java.exercise.classes;

import java.util.Locale;

public class Radio {
    private boolean on;
    private int volume;
    private double frequency;

    public Radio() {
        this.on = true;
        this.volume = 50;
        this.frequency = 85.0;
    }

    public Radio(boolean on, int volume, double frequency) {
        this.on = on;
        this.volume = volume;
        this.frequency = frequency;
    }

    boolean getOn() { return this.on; }
    int getVolume() { return this.volume; }
    double getFrequency() { return this.frequency; }

    void incVolume() {
        this.volume = this.volume < 100 ? this.volume + 1 : 100;
    }

    void decVolume() {
        this.volume = this.volume > 0 ? this.volume - 1 : 0;
    }

    void turnOn() { this.on = true; }
    void turnOff() { this.on = false; }

    void setFrequency(double frequency) {
        this.frequency = frequency <= 110.0 && frequency >= 85.0 ? frequency : 99.9d;
    }

    public String toString() {
        String onString = this.on ? "an" : "aus";
        return String.format(Locale.US, "Radio %s: Frequency = %3.1f Volume = %3d MHz", onString, this.frequency, this.volume);
    }
}
