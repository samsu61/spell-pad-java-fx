package spellpad.swing.autocomplete;

import java.util.TimerTask;

/**
 *
 * @author Jesse Allen
 */
public class RecountMethod extends TimerTask implements Runnable {

    WordCountCache cache;

    public RecountMethod(WordCountCache cache) {
        this.cache = cache;
    }

    @Override
    public void run() {
        cache.reset();
        new Thread(cache).start();
    }
}