package com.example.vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Random;

public class Audio {
    // public MediaView mediaView;
    private Parent root;

    public MediaPlayer getMp() { return mp; }

    private MediaPlayer mp;
    public Audio() {
    }
    public BouncingController getBC() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("bouncing-view.fxml"));
        root = loader.load();
        BouncingController bc = loader.getController();
        return bc;
    }

    public void sfxPlayAudio(){
        try {
            String path = "src/main/resources/com/example/vidmot/Audio/BounceDownSong.wav";
            URI uri = new File(path).toURI();
            Media media = new Media(uri.toString());
            mp = new MediaPlayer(media);
            getBC().mediaView.setMediaPlayer(mp);
            mp.setVolume(7);
            mp.setCycleCount(MediaPlayer.INDEFINITE);
            mp.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sfxAudioJump(){
        try {
            String path = "";
            Media[] media = new Media[6];
            URI[] uri = new URI[6];
            for(int i = 0; i < 6; i++) {
                path = "src/main/resources/com/example/vidmot/Audio/jump" + (i + 1) + ".aif";
                uri[i] = new File(path).toURI();
                media[i] = new Media(uri[i].toString());
            }
            Random rand = new Random();
            int randInt = rand.nextInt(6);
            MediaPlayer jump = new MediaPlayer(media[randInt]);
            getBC().mediaView.setMediaPlayer(jump);
            jump.setVolume(0.25);
            jump.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sfxGameOver(){
        Duration current = mp.getCurrentTime();
        Duration loop = current.add(Duration.seconds(1.0));
        mp.setStartTime(current);
        mp.setStopTime(loop);
        mp.setCycleCount(50);
        mp.play();
        mp.setRate(0.5);
        /*Timeline tapestop = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(mp.rateProperty(), 1)),
                new KeyFrame(Duration.seconds(5), new KeyValue(mp.rateProperty(), 0.0))
        );

        tapestop.play();*/
    }
    }

/*   Media[] media = new Media[6];
        for(int i = 0; i < 6; i++) {
        String jumpFile = "com.example.vidmot/Audio/jump" + (i + 1) + ".aif";
        media[i] = new Media(jumpFile);
        }
        Random rand = new Random();
        int randInt = rand.nextInt(6);
*/