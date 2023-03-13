package com.example.vidmot;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

// g jasdpvjaæfdsjaklæ
public class BoltiC extends ImageView implements LeikHluturInterface {

    @FXML
    private BoltiC fxBolti;
    private PallurC onIt = null;
    private BouncingController bc;
    private Audio audio = new Audio();
    private LeikbordC leikbord;
    // private final double OFFSET = 1;
    private double velocityY = 0;
    private boolean isJumping = false;
    public PallurC getOnIt() {
        return onIt;
    }

    public void setOnIt(PallurC onIt) {
        this.onIt = onIt;
    }

    public void setStefna(int gradur) {
        fxBolti.setRotate(gradur);
    }

    public BoltiC() {
        FXML_Lestur.lesa(this, "bolti-view.fxml");
    }

    @Override
    public void afram() {
        if (getRotate() == Stefna.HAEGRI.getGradur()) {
            setLayoutX(getLayoutX() + 15);
        } else if (getRotate() == Stefna.VINSTRI.getGradur()) {
            //setTranslateZ(fxBolti.getBoundsInLocal().getWidth() / 2.0);
            //setRotationAxis(Rotate.Y_AXIS);
            setLayoutX(getLayoutX() - 15);
        } else if (getRotate() == Stefna.NIDUR.getGradur()) {
            setLayoutY(getLayoutY() + 10);
        } else if (getRotate() == Stefna.UPP.getGradur() && !isJumping) {
            KeyFrame jumpFrame1 = new KeyFrame(Duration.ZERO, new KeyValue(layoutYProperty(), getLayoutY()));
            KeyFrame jumpFrame2 = new KeyFrame(Duration.millis(300), new KeyValue(layoutYProperty(), getLayoutY() - 50));
            Timeline jumpTime = new Timeline(jumpFrame1, jumpFrame2);
            jumpTime.setCycleCount(1);
            jumpTime.play();
            audio.sfxAudioJump();

       // } ballAtBorder();
    }
    /*   public void ballAtBorder(){
        // LeikbordC leikbord = (LeikbordC) this.getParent();
        if (fxBolti.getLayoutX() >= leikbord.getWidth() - getFitWidth()) {
            fxBolti.setLayoutX(0);
            audio.sfxAudioJump();
        }
        if (fxBolti.getLayoutX() <= leikbord.getWidth() - getFitWidth()) {
                fxBolti.setLayoutX(0);
        }
    } */
 }}


   /* @Override
    public void afram() {
        LeikbordC parent = (LeikbordC) this.getParent();
        setX((int) (getX() < 0 || getX() > parent.getWidth() ? 0 : getX() + Math.cos(Math.toRadians
                (stefna.getGradur())) * hradi) % (parent.getWidth() - getImage().getWidth()));
        setY((int) (getY() - Math.sin(Math.toRadians(stefna.getGradur()) * hradi) % parent.getHeight()));
    } */



/*
@Override
public void afram() {
    TranslateTransition tr = new TranslateTransition();
    tr.setNode(fxBolti);
    tr.setDuration(Duration.millis(1000));
    if (fxBolti.getRotate() == Stefna.HAEGRI.getGradur()) {

    } else if (fxBolti.getRotate() == Stefna.VINSTRI.getGradur()) {

    } else if (fxBolti.getRotate() == Stefna.NIDUR.getGradur()) {

    } else if (fxBolti.getRotate() == Stefna.UPP.getGradur()) {
        tr.setByY(250);
        tr.play();
    }
 */



