package com.example.vidmot;

import javafx.animation.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BoltiC extends ImageView implements LeikHluturInterface {

    private PallurC onIt = null;
    private Audio audio = new Audio();
    private Timeline jumpTime;
    public PallurC getOnIt() {
        return onIt;
    }

    public void setOnIt(PallurC onIt) {
        this.onIt = onIt;
    }

    public void setStefna(int gradur) {
        setRotate(gradur);
    }

    public BoltiC() { FXML_Lestur.lesa(this, "bolti-view.fxml"); }

    private void createTimeline(KeyFrame kf1, KeyFrame kf2, int cycles){
        this.jumpTime = new Timeline(kf1, kf2);
        jumpTime.setCycleCount(cycles);
        jumpTime.play();
    }

    @Override
    public void afram() {
        if (getRotate() == Stefna.HAEGRI.getGradur()) {
            setLayoutX(getLayoutX() + 15);
        } else if (getRotate() == Stefna.VINSTRI.getGradur()) {
            setLayoutX(getLayoutX() - 15);
        } else if (getRotate() == Stefna.NIDUR.getGradur()) {
            setLayoutY(getLayoutY() + 20);
        } else if (getRotate() == Stefna.UPP.getGradur()) {
            KeyFrame jumpFrame1 = new KeyFrame(Duration.ZERO, new KeyValue(layoutYProperty(), getLayoutY()));
            KeyFrame jumpFrame2 = new KeyFrame(Duration.millis(10), new KeyValue(layoutYProperty(), getLayoutY() - 50));createTimeline(jumpFrame1, jumpFrame2, 1);
            audio.sfxAudioJump();
        }
        ballAtBorder();
    }
    public void ballAtBorder() {
        if (getLayoutX() > 600) {
            setLayoutX(0);
        } else if (getLayoutX() < 0) {
            setLayoutX(600);
        }
    }
}




