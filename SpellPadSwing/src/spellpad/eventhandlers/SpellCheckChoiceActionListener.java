/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spellpad.eventhandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

/**
 *
 * @author Jesse Allen
 */
public class SpellCheckChoiceActionListener implements ActionListener {

    private JMenuItem item;
    private boolean useDictionary;
    
    public SpellCheckChoiceActionListener(JMenuItem toModify) {
        item = toModify;
        useDictionary = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(useDictionary){
            item.setText("Dictionary");
        }else{
            item.setText("Document");
        }
        useDictionary = !useDictionary;
    }
}