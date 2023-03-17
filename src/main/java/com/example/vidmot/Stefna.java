package com.example.vidmot;

public enum Stefna {
    VINSTRI(180), HAEGRI(0), NIDUR(270), UPP(90);

    public int getGradur() { return gradur;}
    private int gradur;
    Stefna(int gradur) {
        this.gradur = gradur;
    }
}
