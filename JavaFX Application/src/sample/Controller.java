package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class Controller {

    @FXML
    private Label label;


    @FXML
    private GridPane gridPane;


    @FXML
    private Button button4;

    @FXML
    private WebView webView;

    public void initialize() {
        button4.setEffect(new Reflection());
    }


    @FXML
    public void handleMouseEnter() {
        label.setScaleX(2.0);
        label.setScaleY(2.0);
    }

    @FXML
    public void handleMouseExit() {
        label.setScaleX(1.0);
        label.setScaleY(1.0);
    }

    @FXML
    public void handleClick() {
//        FileChooser chooser = new FileChooser();
//        chooser.showOpenDialog(gridPane.getScene().getWindow());

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save Application File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Zip", "*.zip"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*png", "*gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );


        // showDialog parameter is the stage that owns the Directory Chooser, owning means the stage which it is from.
        List<File> file = chooser.showOpenMultipleDialog(gridPane.getScene().getWindow());
        if(file != null) {
            for(int i=0; i < file.size(); i++) {
                System.out.println(file.get(i));
            }
        } else {
            System.out.println("Chooser was cancelled");
        }


    }

    @FXML
    public void handleLinkClicked() {
//        try {
//            Desktop.getDesktop().browse(new URI("http://www.javafx.com"));
//        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
//        }

        WebEngine engine = webView.getEngine();
        engine.load("http://www.javafx.com");


    }




}
