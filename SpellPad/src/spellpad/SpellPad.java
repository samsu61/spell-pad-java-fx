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
import spellpad.eventhandlers.OpenEventHandler;
import spellpad.eventhandlers.SaveEventHandler;
import spellpad.testing.UnitTests;

/**
 *
 * @author Jesse Allen
 */
public class SpellPad extends Application {

    boolean runTests = true;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        if(runTests){
            boolean allTestsPass = UnitTests.RunTests();
            System.out.println("All tests pass: " + allTestsPass);
        }
        
        
        //Build root pane
        BorderPane root = new BorderPane();
        
        //Build Menubar
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        
        KeyCodeCombination controlO = new KeyCodeCombination(
                KeyCode.O, 
                KeyCombination.CONTROL_DOWN);
        openItem.setAccelerator(controlO);
        
        KeyCodeCombination controlS = new KeyCodeCombination(
                KeyCode.S,
                KeyCombination.CONTROL_DOWN);
        saveItem.setAccelerator(controlS);
        
        TextArea texty = new TextArea();
        texty.setOnKeyTyped(new OnKeyTyped());
        texty.getOnKeyTyped();
        texty.setWrapText(true);
        
        openItem.setOnAction(new OpenEventHandler(texty));
        saveItem.setOnAction(new SaveEventHandler(texty));
                
        file.getItems().add(openItem);
        menuBar.getMenus().add(file);
        Menu save = new Menu("Save");
        menuBar.getMenus().add(save);
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
