module com.example.bounceball {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.vidmot to javafx.fxml;
    exports com.example.vidmot;
}