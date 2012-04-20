package spellpad.eventhandlers.textmodifying;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import spellpad.swing.autocomplete.WordCountCache;

/**
 *
 * @author Jesse
 */
public class DocumentChangedActionListener implements DocumentListener {

    private Thread recountThread = new Thread();
    private RecountMethod theRecount = new RecountMethod();
    WordCountCache cache;

    public DocumentChangedActionListener(WordCountCache cache) {

        this.cache = cache;
    }

    @Override
    public void insertUpdate(DocumentEvent de) {
        scheduleRecount();

    }

    @Override
    public void removeUpdate(DocumentEvent de) {
        scheduleRecount();
    }

    @Override
    public void changedUpdate(DocumentEvent de) {
        System.out.println("changed");
    }

    private void scheduleRecount() {
        System.out.println("Is alive:" + recountThread.isAlive());
        if (recountThread.isAlive()) {
            theRecount.waitLonger();
        } else {
            recountThread = new Thread(theRecount);
            recountThread.start();
        }
    }

    class RecountMethod implements Runnable {

        boolean runMe = false;

        @Override
        public void run() {
            try {
                while (!runMe) {
                    runMe = true;
                    Thread.sleep(825);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(RecountMethod.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("I printed");
            cache.reset();
            new Thread(cache).start();
            runMe = false;
        }

        void waitLonger() {
            runMe = false;
        }
    }
}