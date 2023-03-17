package com.example.vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class BouncingApplication extends Application {
    private BouncingController bc;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BouncingApplication.class.getResource("bouncing-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("CSS/bouncingball.css").toExternalForm());
        scene.getRoot().requestFocus();
        bc = fxmlLoader.getController();
        stage.setTitle("Bouncedown!");
        stage.setMaximized(true);
        stage.setMaxHeight(800);
        stage.setMaxWidth(600);
        bc.orvatakkar();
        bc.startGame();
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}