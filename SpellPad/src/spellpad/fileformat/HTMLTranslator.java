package spellpad.fileformat;

/**
 *
 * @author Jesse
 */
public class HTMLTranslator {
    
    public static String parseHTML(String htmlstring){
        htmlstring = htmlstring.replace("<", "(");
        htmlstring = htmlstring.replace(">", ")");
        htmlstring = htmlstring.replace("/", "~");
        return htmlstring;
    }
}
