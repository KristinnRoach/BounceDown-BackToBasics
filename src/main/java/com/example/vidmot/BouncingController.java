package com.example.vidmot;

import com.example.vinnsla.Leikur;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import java.util.HashMap;

public class BouncingController {
    @FXML
    private Button fxMute;
    private Leikur leikurinn;
    private Timeline gameTime;
    @FXML
    protected BorderPane fxRoot;
    @FXML
    private Label fxStig;
    @FXML
    private Label fxHeader;
    @FXML
    private LeikbordC fxLeikbord;

    @FXML
    private MediaView mediaView;
    private Audio audio = new Audio();

    public MediaView getMediaView() { return mediaView; }
    private final HashMap<KeyCode, Stefna> stefnaMap = new HashMap<KeyCode, Stefna>();

    @FXML
    private void initialize() {
        leikurinn = new Leikur();
        this.fxStig.textProperty().bind(leikurinn.stiginProperty().asString());
        setFocus();
        fxHeader.setText("GAME ON !");
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

    private void setFocus(){
        fxLeikbord.getFxBolti().setFocusTraversable(true);
    }

    public void startGame() {
        KeyFrame k = new KeyFrame(Duration.millis(70),
                e -> {
                    fxLeikbord.afram();
                    leikurinn.haekkaStigin();
                    if (fxLeikbord.boltiABotni()) {
                        leikLokid("GAME OVER *.*");
                    }
                });
        gameTime = new Timeline(k);
        gameTime.setCycleCount(Timeline.INDEFINITE);
        gameTime.play();
        audio.sfxPlayAudio();
    }


    private void leikLokid(String s) {
        gameTime.stop();
        fxHeader.setText(s);
        audio.sfxGameOver();
    }

    private void onActionKeys(KeyEvent event) {
        try {
            if (stefnaMap.get(event.getCode()) == null) {
                event.consume();
            } else {
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

        Scene s = fxStig.getScene();
        s.addEventFilter(KeyEvent.ANY,
                event -> { onActionKeys(event);}
        );
    }
}
