package spellpad.filetype.parsing;

/**
 * @author Jesse
 */
public class SpellpadParser {

   
    
    static public String prepPlainText(String plainText){
        plainText = plainText.replace("\t", "<tab>");
        return plainText.replace("\n", "<br>");
    }
    
    static public String restorePlainText(String htmlText){
        htmlText = htmlText.replace("<tab>", "\t");
        return htmlText.replace("<br>", "\n");
    }
    
    static public String toHTML(String spellpadText){
        return spellpadText;
    }
    
    static public String toSpellPad(String htmlText){
        return htmlText;
    }
}
