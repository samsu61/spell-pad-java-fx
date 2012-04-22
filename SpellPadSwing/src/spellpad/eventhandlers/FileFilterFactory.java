package spellpad.eventhandlers;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Jesse
 */
final class FileFilterFactory {

    static FileFilter getSpellpadFileFilter() {
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Text Files", "txt");
        return extensionFilter;
    }

    static FileChooserDetails getFileFromPopupDialogue(FileAction openOrSave) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("C:/"));
        fileChooser.setFileFilter(FileFilterFactory.getSpellpadFileFilter());
        int response = openOrSave == FileAction.SAVE
                ? fileChooser.showSaveDialog(null)
                : fileChooser.showOpenDialog(null);
        if (response == JFileChooser.CANCEL_OPTION || response == JFileChooser.ERROR_OPTION) {
            System.out.println("not successed");
        }
        FileChooserDetails thisChoosersDetails =
                new FileChooserDetails(
                fileChooser.getSelectedFile(),
                (FileNameExtensionFilter) fileChooser.getFileFilter());        
        return thisChoosersDetails;
    }
}
