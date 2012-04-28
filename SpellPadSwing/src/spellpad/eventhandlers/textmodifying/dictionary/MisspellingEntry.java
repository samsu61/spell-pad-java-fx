package spellpad.eventhandlers.textmodifying.dictionary;

/**
 *
 * @author Jesse Allen
 */
public class MisspellingEntry {

    int begins;
    String text;

    public MisspellingEntry(int position,String word) {
        begins = position;
        text = word;
    }
    
    public String getText(){
        return text;
    }
    
    
}
