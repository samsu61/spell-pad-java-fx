/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spellpad.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import spellpad.eventhandlers.OpenEventActionListener;

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
        JFrame window = new JFrame();
        window.setLayout(new BorderLayout());
        window.setBounds(0, 0, 800, 600);
        window.setLocationRelativeTo(null);
        window.setTitle("SpellPad");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JTextPane textArea = new JTextPane();
        JScrollPane textAreaScrollPane = new JScrollPane(textArea);
        textArea.setPreferredSize(new Dimension(800, 600));
        window.add(textAreaScrollPane, BorderLayout.CENTER);
        
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        open.addActionListener(new OpenEventActionListener(textArea));
        //save.addActionListener(new SaveEventActionListener(textArea));
        
        file.add(open);
        file.add(save);
        menubar.add(file);
        
        
        window.setJMenuBar(menubar);
        
        window.setVisible(true);
    }
}
