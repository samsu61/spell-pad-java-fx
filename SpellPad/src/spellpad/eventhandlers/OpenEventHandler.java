package spellpad.eventhandlers;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooserBuilder;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import spellpad.SpellPad;

public class OpenEventHandler implements EventHandler<ActionEvent> {

    public OpenEventHandler(Scene owner) {
        
    }

    @Override
    public void handle(ActionEvent t) {
        //Build a FileChooser
        FileChooser fileChooser = buildFileChooser();
        //Display the FileChooser and retrieve chosen path
        File chosenFile = fileChooser.showOpenDialog(new PopupWindow() {});
        // Path is either NULL or typeof(FILE)
        if (chosenFile == null) {
            System.out.println("No File Chosen");
            return;
        }
        
        try {
            /*
             * Read in file and put in text area.
             */
            System.out.println(chosenFile.getCanonicalPath());
        } catch (IOException ex) {
            Logger.getLogger(SpellPad.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(t.getSource());
    }

    
    private FileChooser buildFileChooser() {
        FileChooserBuilder fileChooserBuilder = FileChooserBuilder.create();
        fileChooserBuilder.initialDirectory(new File("C:/"));
        fileChooserBuilder.extensionFilters(new FileChooser.ExtensionFilter("Text Files", "*.txt", "*.spellpad"));
        fileChooserBuilder.title("Choose a text file");
        return fileChooserBuilder.build();
    }
}