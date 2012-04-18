package spellpad.swing;

import javax.swing.JTextPane;

/**
 *
 * @author Jesse Allen
 */
public class SpellPadEditorPane extends JTextPane {

    String bodyTag = "<body>";

    /**
     * Seeks to the index that would be the true index if Html tags were not
     * present.
     *
     * @param index the index ignoring html that we seek to
     * @return the index in the actual string
     */
    public int seekIgnoreHtml(int index) {
        String content = getText();
        
        int realIndex = content.indexOf(bodyTag);
        realIndex = realIndex + bodyTag.length();
        char[] realContent = content.toCharArray();
        boolean inTag = false;
        boolean lastWasSpace = false;
        for (int i = realIndex; i < realContent.length && index >= 0; i++) {
            if (lastWasSpace) {
                if (realContent[i] == ' ') {
                    realIndex++;
                    continue;
                }
            } else if (realContent[i] == '<') {
                inTag = true;
                continue;
            } else if (realContent[i] == '>') {
                inTag = false;
                continue;
            } else if (inTag) {
                continue;
            } else if (realContent[i] == ' ') {
                lastWasSpace = true;
            }
            realIndex++;
            index--;
        }
        return realIndex;

    }
}
