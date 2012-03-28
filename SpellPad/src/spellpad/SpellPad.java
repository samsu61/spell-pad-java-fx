/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spellpad;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.filechooser.FileSystemView;

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
        menuBar.getMenus().add(file);
        Menu save = new Menu("Save");
        menuBar.getMenus().add(save);
        TextArea texty = new TextArea();
        
        
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
