package com.example.vidmot;

import com.example.vinnsla.Leikur;
import javafx.animation.*;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class BouncingController {
    @FXML
    private Button fxMute;
    private Leikur leikurinn;
    public Timeline getGameTime() {
        return gameTime;
    }
    private Timeline gameTime;
    //private Animation animation = new Animation();
    @FXML
    protected BorderPane fxRoot;
    @FXML
    private Label fxStig;
    public BorderPane getFxRoot() {
        return fxRoot;
    }
    @FXML
    private Label fxHeader;
    @FXML
    private LeikbordC fxLeikbord;
    @FXML
    private MediaView mediaView;
    private Audio audio = new Audio();
    public BouncingController thisBC() { return this; }

    public MediaView getMediaView() { return mediaView; }

    public LeikbordC getFxLeikbord() {
        return fxLeikbord;
    }

    private final HashMap<KeyCode, Stefna> stefnaMap = new HashMap<KeyCode, Stefna>();

    public HashMap<KeyCode, Boolean> getPressedKeys() { return pressedKeys; }

    public void setPressedKeys(KeyEvent event) {
        switch (event.getCode()) {
            case UP -> pressedKeys.put(KeyCode.UP, true);
            case DOWN -> pressedKeys.put(KeyCode.DOWN, true);
            case LEFT -> pressedKeys.put(KeyCode.LEFT, true);
            case RIGHT -> pressedKeys.put(KeyCode.RIGHT, true);
            default -> {
            }
        }
    }

    private HashMap<KeyCode, Boolean> pressedKeys = new HashMap<>();


    @FXML
    private void initialize() {
        leikurinn = new Leikur();
        this.fxStig.textProperty().bind(leikurinn.stiginProperty().asString());
        setFocus();
        fxHeader.setText("GAME ON");
        // allToFront();
    }

    private void allToFront(){
        fxHeader.toFront();
        fxMute.toFront();
        fxStig.toFront();
    }

    @FXML
    protected void sfxJump() {
        audio.sfxAudioJump();
    }

    @FXML
    protected void muteAudio(ActionEvent event) {
        event.consume();
        if(audio.getMp().isMute()) {
            audio.getMp().setMute(false);
            fxMute.setText("Sound ON");
        }
        else {
            audio.getMp().setMute(true);
           fxMute.setText("Sound OFF");
        }
    }

    private void setFocus(){  // kannski þarf ekki
        // fxStig.setFocusTraversable(false);
        // fxHeader.setFocusTraversable(false);
        // fxMute.setFocusTraversable(false);
        fxLeikbord.getFxBolti().setFocusTraversable(true);
    }

    public void startGame() {
        KeyFrame k = new KeyFrame(Duration.millis(80),    // hvert tímabil er 50 millisek.
                e -> {
                    fxLeikbord.afram();
                    leikurinn.haekkaStigin();
                    if (fxLeikbord.boltiABotni()) {
                        leikLokid("ónóóó");
                    }
                });
        gameTime = new Timeline(k);           // tengjum timeline og tímabilið
        gameTime.setCycleCount(Timeline.INDEFINITE);   // hve lengi tímalínan keyrist
        gameTime.play();
       // increaseSpeed();
        audio.sfxPlayAudio();
    }

  /*  private void increaseSpeed() {
        long startMillis = System.currentTimeMillis();
        Timer timer = new Timer();
        if(timer.equals())
        double newSpeed = fxLeikbord.getSpeed();

        fxLeikbord.setSpeed(newSpeed);
        });
    }*/

   /* private void keyEvents(KeyEvent event) {
        fxStig.getScene().setOnKeyPressed(KeyEvent -> {
            fxLeikbord.getFxBolti().afram(event);
    });} */


    private void leikLokid(String s) {
        gameTime.stop();
       /* int dwn = 1;
        while (dwn>0) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            audio.getMp().setRate(audio.getMp().getRate() -0.1);
        }*/
        fxHeader.setText(s);
        //audio.getMp().setRate(0.5);

        //animation.paint(0, 0, 0);
        audio.sfxGameOver();
    }

    private void onActionKeys(KeyEvent event) {
        try {
            if (stefnaMap.get(event.getCode()) == null) {
                event.consume();
            } else {
                setPressedKeys(event);
                fxLeikbord.getFxBolti().setStefna(stefnaMap.get(event.getCode()).getGradur());
                fxLeikbord.getFxBolti().afram();
            }
        } catch (NullPointerException e) {
            event.consume();
        }
    }



    public void orvatakkar() {

        stefnaMap.put(KeyCode.RIGHT, Stefna.HAEGRI);
        stefnaMap.put(KeyCode.LEFT, Stefna.VINSTRI);
        stefnaMap.put(KeyCode.UP, Stefna.UPP);
        pressedKeys.put(KeyCode.RIGHT, false);
        pressedKeys.put(KeyCode.LEFT, false);
        pressedKeys.put(KeyCode.UP, false);
        pressedKeys.put(KeyCode.DOWN, false);

        Scene s = fxStig.getScene();
        s.addEventFilter(KeyEvent.ANY,
                event -> { onActionKeys(event);}
        );
    }
}
