package spellpad.filetype.parsing;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;

/**
 * @author Jesse
 */
public class SpellpadParser {

    static final String MAGICSTRING = "madsfkjdlkiadflejkaldicislaldkfdleoqopa";

    SpellpadParser() {
    }

    public static String prepPlainText(String plainText) {
        plainText = StringEscapeUtils.escapeHtml4(plainText);
        plainText = "<p style=\"margin-top: 0\">" + plainText;
        plainText = plainText.replace("\n", "</p><p style=\"margin-top: 0\">");
        plainText = plainText.replace("(bold~)", "<b>").replace("(~bold)", "</b>");
        plainText = plainText.replace("(underline~)", "<u>").replace("(~underline)", "</u>");
        plainText = plainText.replace("(italic~)", "<i>").replace("(~italic)", "</i>");
        plainText = plainText.replace("(left~)", "<p style=\"margin-top:0\" align=\"left\">").replace("(~close)", "</p>");
        plainText = plainText.replace("(center~)", "<p style=\"margin-top:0\" align=\"left\">");
        plainText = plainText.replace("(right~)", "<p style=\"margin-top:0\" align=\"right\">");
        return plainText;
    }

    public static String restorePlainText(String htmlText) {
        System.out.println(htmlText);
        htmlText = htmlText.replace("\n", MAGICSTRING).replace("<br>", MAGICSTRING);
        htmlText = htmlText.replace("<b>", "(bold~)").replace("</b>", "(~bold)");
        htmlText = htmlText.replace("<u>", "(underline~)").replace("</u>", "(~underline)");
        htmlText = htmlText.replace("<i>", "(italic~)").replace("</i>", "(~italic)");
        htmlText = htmlText.replace("<p style=\"margin-top: 0\">", "(left~)").replace("</p>", "(~close)");
        htmlText = htmlText.replace("<p style=\"margin-top: 0\" align=\"left\">", "(left~");
        htmlText = htmlText.replace("<p style=\"margin-top: 0\" align=\"center\">", "(center~)");
        htmlText = htmlText.replace("<p style=\"margin-top: 0\" align=\"right\">", "(right~)");
        htmlText = Jsoup.parse(htmlText).text();
        htmlText = htmlText.replace(MAGICSTRING, "\n");
        return StringEscapeUtils.unescapeHtml4(htmlText);
    }

    public static String toHTML(String spellpadText) {
        return spellpadText;
    }

    public static String toSpellPad(String htmlText) {
        return htmlText;
    }
}

/*
 * static public String restorePlainText(String htmlText) { htmlText =
 * htmlText.substring(44); htmlText = htmlText.replace("<p style=\"margin-top:
 * 0\">", "").replace("</p>", ""); htmlText = htmlText.substring(0,
 * htmlText.length()-22); htmlText = StringEscapeUtils.unescapeHtml4(htmlText);
 * return htmlText; }
 *
 */
    
    /*static public String restorePlainText(String htmlText) {
        htmlText = htmlText.replace("<p style=\"margin-top: 0\">", "\r\n");
        htmlText = Jsoup.parse(htmlText).text();
        htmlText = StringEscapeUtils.unescapeHtml4(htmlText);
        return htmlText;
    }
*/