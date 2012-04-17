package spellpad.filetype.parsing;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;

/**
 * @author Jesse
 */
public class SpellpadParser {

    SpellpadParser(){
        
    }
    
    public static String prepPlainText(String plainText) {
        //plainText = StringEscapeUtils.escapeHtml4(plainText);
        plainText = plainText.replace("\n", "<br>");
        return plainText;
    }
    
    public static String restorePlainText(String htmlText){
        htmlText = Jsoup.parse(htmlText).text();
        return StringEscapeUtils.unescapeHtml4(htmlText);
    }

    /*static public String restorePlainText(String htmlText) {
        htmlText = htmlText.substring(44);
        htmlText = htmlText.replace("<p style=\"margin-top: 0\">", "").replace("</p>", "");
       htmlText = htmlText.substring(0, htmlText.length()-22);
        htmlText = StringEscapeUtils.unescapeHtml4(htmlText);
        return htmlText;
    }
    * 
    */
    
    /*static public String restorePlainText(String htmlText) {
        htmlText = htmlText.replace("<p style=\"margin-top: 0\">", "\r\n");
        htmlText = Jsoup.parse(htmlText).text();
        htmlText = StringEscapeUtils.unescapeHtml4(htmlText);
        return htmlText;
    }
*/
    public static String toHTML(String spellpadText) {
        return spellpadText;
    }

    public static String toSpellPad(String htmlText) {
        return htmlText;
    }
}
