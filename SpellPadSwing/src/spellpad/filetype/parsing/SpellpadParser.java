package spellpad.filetype.parsing;

import org.jsoup.Jsoup;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @author Jesse
 */
public class SpellpadParser {

    static public String prepPlainText(String plainText) {
        plainText = StringEscapeUtils.escapeHtml4(plainText);
        plainText = plainText.replace("\n", "<br>");
        return plainText;
    }

    static public String restorePlainText(String htmlText) {
        htmlText = htmlText.substring(44);
        htmlText = htmlText.replace("<p style=\"margin-top: 0\">", "").replace("</p>", "");
       htmlText = htmlText.substring(0, htmlText.length()-22);
        htmlText = StringEscapeUtils.unescapeHtml4(htmlText);
        return htmlText;
    }
    
    /*static public String restorePlainText(String htmlText) {
        htmlText = htmlText.replace("<p style=\"margin-top: 0\">", "\r\n");
        htmlText = Jsoup.parse(htmlText).text();
        htmlText = StringEscapeUtils.unescapeHtml4(htmlText);
        return htmlText;
    }
*/
    static public String toHTML(String spellpadText) {
        return spellpadText;
    }

    static public String toSpellPad(String htmlText) {
        return htmlText;
    }
}
