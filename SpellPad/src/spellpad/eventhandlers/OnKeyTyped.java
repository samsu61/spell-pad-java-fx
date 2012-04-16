package spellpad.eventhandlers;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Jesse
 */
public class OnKeyTyped implements EventHandler<KeyEvent>, Runnable {

    KeyEvent myEvent = null;

    public OnKeyTyped() {
        super();
    }

    @Override
    public void handle(KeyEvent arg0) {
        synchronized (OnKeyTyped.class) {
            myEvent = arg0;
            Thread thisThreaded = new Thread(this.clone());
            thisThreaded.start();
        }
    }

    void threadedHandle(KeyEvent arg0) throws InterruptedException {
        //Autocomplete
        //Spell check
        //Thread.sleep(1000);
        //Add to local tree for current document
        System.out.println(myEvent);
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void run() {
        try {
            threadedHandle(myEvent);
        } catch (InterruptedException ex) {
            Logger.getLogger(OnKeyTyped.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public OnKeyTyped clone() {
        OnKeyTyped thisCloned = new OnKeyTyped();
        thisCloned.myEvent = this.myEvent;
        return thisCloned;
    }
}