package spellpad.swing.autocomplete;

import java.util.Comparator;

/**
 *
 * @author Jesse Allen
 */
public class Entry implements Comparable<Entry> {

    String word;
    int count;

    public Entry(int count, String value) {
        this.count = count;
        word = value;
    }

    public void seenAnother() {
        count++;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCount() {
        return count;
    }

    public String getWord() {
        return word;
    }

    @Override
    public int compareTo(Entry o) {
        if (this.word.length() == o.getWord().length()) {
            return this.word.compareTo(o.getWord());
        }
        return this.word.length() - o.getWord().length();
    }
}