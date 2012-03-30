/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spellpad.eventhandlers;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Popup;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author Jesse
 */
public class OnKeyTyped implements EventHandler<KeyEvent>, Runnable {

    static int j = 0;
    KeyEvent myEvent = null;

    public OnKeyTyped() {
        super();
        System.out.println(++j);
    }

    @Override
    public void handle(KeyEvent arg0) {
        synchronized (OnKeyTyped.class) {
            myEvent = arg0;
            Thread t = new Thread(this.clone());
            t.start();
        }
    }

    void threadedHandle(KeyEvent arg0) throws InterruptedException {
        //Autocomplete
        //Spell check
        Thread.sleep(1000);
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
        OnKeyTyped okt = new OnKeyTyped();
        okt.myEvent = this.myEvent;
        return okt;
    }
}
