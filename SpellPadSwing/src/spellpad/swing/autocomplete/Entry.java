package spellpad.swing.autocomplete;

import java.util.Comparator;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entry other = (Entry) obj;
        if (!Objects.equals(this.word, other.word)) {
            return false;
        }
        if (this.count != other.count) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.word);
        hash = 79 * hash + this.count;
        return hash;
    }
}