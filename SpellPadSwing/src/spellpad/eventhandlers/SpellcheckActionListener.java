/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spellpad.eventhandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextPane;
import spellpad.eventhandlers.textmodifying.dictionary.DictionaryController;
import spellpad.swing.autocomplete.WordCountCache;

/**
 *
 * @author Jesse Allen
 */
public class SpellcheckActionListener implements ActionListener{

    JTextPane textArea;
    WordCountCache cache;
    
    public SpellcheckActionListener(JTextPane textPane, WordCountCache dictionaryOwner){
        textArea = textPane;
        cache = dictionaryOwner;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        DictionaryController controller = new DictionaryController(textArea, cache);
        controller.spellCheckInvoked();
    }
    
}
