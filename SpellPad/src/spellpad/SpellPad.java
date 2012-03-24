/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spellpad;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Jesse Allen
 */
public class SpellPad extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        menuBar.getMenus().add(file);
        Menu save = new Menu("Save");
        menuBar.getMenus().add(save);
        TextArea texty = new TextArea();
        texty.setWrapText(true);
        texty.setTranslateY(25);
        texty.maxHeight(100);
        root.getChildren().add(menuBar);
        root.alignmentProperty().setValue(Pos.TOP_CENTER);
        root.getChildren().add(texty);
        
        primaryStage.setScene(new Scene(root, 800, 600));

        primaryStage.show();

    }
}
