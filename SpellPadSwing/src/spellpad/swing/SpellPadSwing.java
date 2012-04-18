package spellpad.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.parser.ParserDelegator;
import javax.swing.undo.UndoManager;
import spellpad.eventhandlers.OpenEventActionListener;
import spellpad.eventhandlers.SaveEventActionListener;
import spellpad.eventhandlers.mouse.MouseListener;
import spellpad.eventhandlers.textmodifying.BoldEventListener;
import spellpad.eventhandlers.textmodifying.ItalicsEventListener;
import spellpad.eventhandlers.textmodifying.UnderlineEventListener;

/**
 * @author Jesse
 */
public class SpellPadSwing {

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final String CONTENT_TYPE = "text/html";
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        SpellPadSwing spellpad = new SpellPadSwing();
        spellpad.init();
    }

    public void init() {
        setLookAndFeel();
        JFrame window = initFrame();
        SpellPadEditorPane editPane = initTextPane();
        JScrollPane textAreaScrollPane = addScrolling(editPane, window);
        JMenuBar menubar = initMenuBar(editPane);
        JToolBar toolBar = initToolBar(editPane);
       
        textAreaScrollPane.setDoubleBuffered(true);
        editPane.setDoubleBuffered(true);
        window.setJMenuBar(menubar);
        window.add(toolBar, BorderLayout.PAGE_START);
        
        UndoManager undoManager = new UndoManager();
        editPane.getDocument().addUndoableEditListener(undoManager);
        
        
        editPane.requestFocus();
        window.setVisible(true);
    }

    private JToolBar initToolBar(SpellPadEditorPane editPane) {
        JToolBar toolBar = new JToolBar("Text Controls");
        JButton boldButton = new JButton("B");
        JButton italicButton = new JButton("I");
        JButton underlineButton = new JButton("U");
        boldButton.addActionListener(new BoldEventListener(editPane));
        italicButton.addActionListener(new ItalicsEventListener(editPane));
        underlineButton.addActionListener(new UnderlineEventListener(editPane));
        toolBar.add(boldButton);
        toolBar.add(italicButton);
        toolBar.add(underlineButton);
        return toolBar;
    }

    private JMenuBar initMenuBar(SpellPadEditorPane editPane) {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem exit = new JMenuItem("Exit");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        open.addActionListener(new OpenEventActionListener(editPane));
        save.addActionListener(new SaveEventActionListener(editPane));
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file.add(open);
        file.add(save);
        file.add(exit);
        menubar.add(file);
        return menubar;
    }

    private JScrollPane addScrolling(SpellPadEditorPane editPane, JFrame window) {
        JScrollPane textAreaScrollPane = new JScrollPane(editPane);
        editPane.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        editPane.addMouseListener(new MouseListener(editPane));
        window.add(textAreaScrollPane, BorderLayout.CENTER);
        return textAreaScrollPane;
    }

    private SpellPadEditorPane initTextPane() {
        SpellPadEditorPane editPane = new SpellPadEditorPane();
        HTMLDocument document = new HTMLDocument();
        document.setParser(new ParserDelegator());
        editPane.setDocument(document);
        editPane.setContentType(CONTENT_TYPE);
        return editPane;
    }

    protected void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                UnsupportedLookAndFeelException e) {
            
        }
    }

    private JFrame initFrame() throws HeadlessException {
        JFrame window = new JFrame();
        window.setLayout(new BorderLayout());
        window.setBounds(0, 0, 800, 600);
        window.setLocationRelativeTo(null);
        window.setTitle("SpellPad");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return window;
    }
}
