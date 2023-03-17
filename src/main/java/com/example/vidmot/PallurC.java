package com.example.vidmot;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class PallurC extends ImageView implements LeikHluturInterface {

    private BouncingController bc;
    private LeikbordC leikbord;

    @FXML
    private PallurC fxPallur;

    public PallurC() {
        try {
            FXML_Lestur.lesa(this, "pallur-view.fxml");
        } catch (Exception e) {
            System.out.println("lesaFxml error í pallur");
        }
    }

    public void afram() {
        // LeikbordC leikbord = bc.getFxLeikbord();
        leikbord = (LeikbordC) this.getParent();
        ObservableList<PallurC> pList= leikbord.getFxPallar();
        for (PallurC p : pList ) {
            if (p.getLayoutY() <= 0) {
                int a = (int) (Math.random() * leikbord.getWidth() - p.getFitWidth());
                int b = (int) (Math.random() * leikbord.getWidth() - p.getFitWidth());
                p.setLayoutY(b + leikbord.getHeight());
                p.setLayoutX(a);
        }
            if (!(p == pList.get(6))) { p.setLayoutY((p.getLayoutY()-0.4) * leikbord.getSpeed()); }
            else { p.setLayoutY((p.getLayoutY()-1.4) * leikbord.getSpeed()); }
    }
    }
  /*  public void speedyPallur() {

    }
          */

}



