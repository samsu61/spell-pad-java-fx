package spellpad.eventhandlers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.FileChooserBuilder;
import javafx.stage.PopupWindow;

public class OpenEventHandler implements EventHandler<ActionEvent> {

    TextArea area = null;
    
    public OpenEventHandler(TextArea htmlTextArea) {
        area = htmlTextArea;
    }

    @Override
    public void handle(ActionEvent t) {
        //Handle possible global errors
        if(t == null || area == null) return;
        
        
        //Build a FileChooser
        FileChooser fileChooser = buildFileChooser();
        //Display the FileChooser and retrieve chosen path
        File chosenFile = fileChooser.showOpenDialog(new PopupWindow() {});
        // Path is either NULL or typeof(FILE)
        if (chosenFile == null) {
            System.out.println("No File Chosen");
            return;
        }
        StringBuilder fileContents = new StringBuilder();
        try {
            FileReader reader = new FileReader(chosenFile);
            char[] fileCharacters = new char[(int)chosenFile.length()];
            reader.read(fileCharacters);
            fileContents.append(fileCharacters);
            area.setText(fileContents.toString());
        } catch (IOException ex) {
            Logger.getLogger(OpenEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private FileChooser buildFileChooser() {
        FileChooserBuilder fileChooserBuilder = FileChooserBuilder.create();
        fileChooserBuilder.initialDirectory(new File("C:/"));
        fileChooserBuilder.extensionFilters(new FileChooser.ExtensionFilter("Text Files", "*.txt", "*.spellpad"));
        fileChooserBuilder.title("Choose a text file");
        return fileChooserBuilder.build();
    }
}