package spellpad.eventhandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import spellpad.eventhandlers.SaveEventActionListener.FileFilterFactory;

/**
 *
 * @author Jesse
 */
public class OpenEventActionListener implements ActionListener {

    private JEditorPane textArea;

    public OpenEventActionListener(JEditorPane text) {
        textArea = text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e == null) {
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(FileFilterFactory.getSpellpadFileFilter());
        int response = fileChooser.showOpenDialog(textArea.getParent());
        if(response == JFileChooser.CANCEL_OPTION || response == JFileChooser.ERROR_OPTION){
            return;
        }
        File chosenFile = fileChooser.getSelectedFile();
        if (chosenFile == null) return;
        if (!chosenFile.isFile()) return;
        StringBuilder fileContents = new StringBuilder();
        try {
            FileReader reader = new FileReader(chosenFile);
            char[] fileCharacters = new char[(int)chosenFile.length()];
            reader.read(fileCharacters);
            fileContents.append(fileCharacters);
            textArea.setText(fileContents.toString());
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }  
    }
}
