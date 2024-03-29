package spellpad.eventhandlers;

import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Jesse
 */
public class FileChooserDetails {

    private File file;
    private FileNameExtensionFilter extensionFilter;

    public FileChooserDetails(File file, FileNameExtensionFilter filter) {
        this.file = file;
        extensionFilter = filter;
    }

    public File getFile() {
        return file;
    }

    public FileNameExtensionFilter getExtensionFilter() {
        return extensionFilter;
    }
}
