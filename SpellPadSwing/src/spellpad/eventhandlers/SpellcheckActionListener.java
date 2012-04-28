package spellpad.eventhandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import spellpad.eventhandlers.textmodifying.dictionary.DictionaryController;
import spellpad.swing.autocomplete.WordCountCache;

/**
 *
 * @author Jesse Allen
 */
public class SpellcheckActionListener implements ActionListener {

    JFrame mainWindow;
    JTextPane textArea;
    WordCountCache cache;

    public SpellcheckActionListener(JTextPane textPane, WordCountCache dictionaryOwner, JFrame dispatcher) {
        textArea = textPane;
        cache = dictionaryOwner;
        mainWindow = dispatcher;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DictionaryController controller = new DictionaryController(textArea, cache, mainWindow);
        controller.spellCheckInvoked();
    }
}
