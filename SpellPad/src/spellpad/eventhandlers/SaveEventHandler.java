package spellpad.eventhandlers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.FileChooserBuilder;
import javafx.stage.PopupWindow;

/**
 *
 * @author Jesse
 */
public class SaveEventHandler implements EventHandler<ActionEvent> {

    private HTMLEditor textAreaBound;
    
    public SaveEventHandler(HTMLEditor textArea){
        textAreaBound = textArea;
    }
    
    @Override
    public void handle(ActionEvent t) {
        if(t == null || textAreaBound == null)return;
         //Build a FileChooser
        FileChooser fileChooser = buildFileChooser();
        //Display the FileChooser and retrieve chosen path
        File chosenFile = fileChooser.showSaveDialog(new PopupWindow() {});
        //Check file path
        if(chosenFile == null){
            System.out.println("Save aborted");
            return;
        }
//        String fileContents = textAreaBound.getText();
        String fileContents = textAreaBound.getHtmlText();
        try{
            FileOutputStream fileOS = new FileOutputStream(chosenFile);
            PrintWriter writer = new PrintWriter(fileOS);
            writer.print(fileContents);
            writer.flush();
            writer.close();
        }catch(IOException ex){
            Logger.getLogger(SaveEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private FileChooser buildFileChooser() {
        FileChooserBuilder fileChooserBuilder = FileChooserBuilder.create();
        fileChooserBuilder.initialDirectory(new File("C:/"));
        fileChooserBuilder.extensionFilters(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        fileChooserBuilder.title("Save the text file");
        return fileChooserBuilder.build();
    }
    
}
