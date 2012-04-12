/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spellpad.eventhandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;

/**
 *
 * @author Jesse
 */
public class OpenEventActionListener implements ActionListener {

    private JTextPane textArea;

    public OpenEventActionListener(JTextPane text) {
        textArea = text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e == null) {
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
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
