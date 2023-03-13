package com.example.vidmot;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class FXML_Lestur {
    public static void lesa(Object controller, String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader(FXML_Lestur.class.getResource(fxml));
        fxmlLoader.setClassLoader(controller.getClass().getClassLoader());
        fxmlLoader.setRoot(controller);
        fxmlLoader.setController(controller);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
