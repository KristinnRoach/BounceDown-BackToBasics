package com.example.vinnsla;
/*
Skrifaðu klasann Leikur í vinnslunni. Leikur heldur utan um stigin (IntegerProperty).
Leikur hefur aðferð eins og stiginProperty() og public void haekkaStigin() sem hækkar stigin um einn.
Seinna ef þú ætlar að láta hraðann vera mismunandi getur verið gott að hafa hraðann hér í Leikur.
Í BouncingController skaltu binda stigaviðmótshlutinn við stigin í Leikur
 */

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Leikur {
    private IntegerProperty stigin = new SimpleIntegerProperty(0);
    public Leikur() { }
    public IntegerProperty stiginProperty() {
        return stigin;
    }
    public int stiginInt() {
        return stigin.get();
    }
    public void setStiginProperty(int stig) {
        this.stigin.set(stig);
    }

    public void haekkaStigin() {
        setStiginProperty(stiginInt()+1);
    }
}
