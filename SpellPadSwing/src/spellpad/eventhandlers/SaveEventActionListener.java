package spellpad.eventhandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Jesse
 */
public class SaveEventActionListener implements ActionListener{
    
    private JEditorPane textArea;
    
    public SaveEventActionListener(JEditorPane text){
        textArea = text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e == null || textArea == null) return;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(FileFilterFactory.getSpellpadFileFilter());
        int response = fileChooser.showSaveDialog(textArea.getParent());
        if(response == JFileChooser.CANCEL_OPTION || response == JFileChooser.ERROR_OPTION){
            System.out.println("not successed");
            return;
        }
        File chosenFile = fileChooser.getSelectedFile();
        if(chosenFile == null ){
            System.out.print("null file");
            return;
        }
        String textInArea = textArea.getText();
        try{
            FileWriter fileWriter = new FileWriter(new File(chosenFile.getAbsoluteFile() + ".txt"));
            PrintWriter printer = new PrintWriter(fileWriter);
            printer.print(textInArea);
            printer.flush();
            printer.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    static class FileFilterFactory{
        static FileFilter getSpellpadFileFilter(){
            FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Text Files", "txt");
            return extensionFilter;
        }
    }
}
