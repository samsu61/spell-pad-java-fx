/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spellpad;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import spellpad.eventhandlers.OnKeyTyped;

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
        
        BorderPane root = new BorderPane();
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu open = new Menu("Open");
        MenuItem add = new MenuItem("Open");
        KeyCodeCombination kcc = new KeyCodeCombination(
                KeyCode.O, 
                KeyCombination.CONTROL_DOWN);
        add.setAccelerator(kcc);
        file.getItems().add(open);
        menuBar.getMenus().add(file);
        Menu save = new Menu("Save");
        menuBar.getMenus().add(save);
        TextArea texty = new TextArea();
        texty.setOnKeyTyped(new OnKeyTyped());
        
        texty.getOnKeyTyped();
        texty.setWrapText(true);
        primaryStage.setTitle("Spell Pad");
        TreeView tree = new TreeView();
        tree.setPrefWidth(150.0);
        root.setTop(menuBar);
        root.setCenter(texty);
        root.setLeft(tree);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

    }
}
