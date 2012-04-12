package spellpad.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import spellpad.eventhandlers.OpenEventActionListener;
import spellpad.eventhandlers.SaveEventActionListener;

/**
 * @author Jesse
 */
public class SpellPadSwing {

    public static void main(String[] args) {
        // TODO code application logic here
        SpellPadSwing spellpad = new SpellPadSwing();
        spellpad.init();
    }

    public void init() {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(ClassNotFoundException | 
                InstantiationException | 
                IllegalAccessException | 
                UnsupportedLookAndFeelException e){
            e.printStackTrace();
        }
        JFrame window = new JFrame();
        window.setLayout(new BorderLayout());
        window.setBounds(0, 0, 800, 600);
        window.setLocationRelativeTo(null);
        window.setTitle("SpellPad");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JEditorPane editPane = new JEditorPane();
        editPane.setContentType("text/html");
        JScrollPane textAreaScrollPane = new JScrollPane(editPane);
        editPane.setPreferredSize(new Dimension(800, 600));
        window.add(textAreaScrollPane, BorderLayout.CENTER);
        
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        open.addActionListener(new OpenEventActionListener(editPane));
        save.addActionListener(new SaveEventActionListener(editPane));
        
        file.add(open);
        file.add(save);
        menubar.add(file);
        
        
        window.setJMenuBar(menubar);
        
        window.setVisible(true);
    }
}
