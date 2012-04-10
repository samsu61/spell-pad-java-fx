package spellpad.eventhandlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.web.HTMLEditor;

/**
 *
 * @author Jesse
 */
public class SaveEventHandler implements EventHandler<ActionEvent> {

    private TextArea textAreaBound;
    
    public SaveEventHandler(TextArea textArea){
        textAreaBound = textArea;
    }
    
    @Override
    public void handle(ActionEvent arg0) {
        
       
    }
    
}
