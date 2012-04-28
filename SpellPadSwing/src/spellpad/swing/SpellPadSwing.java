package spellpad.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.parser.ParserDelegator;
import javax.swing.undo.UndoManager;
import spellpad.dictionary.DictionaryLoader;
import spellpad.eventhandlers.OpenEventActionListener;
import spellpad.eventhandlers.SaveEventActionListener;
import spellpad.eventhandlers.SpellCheckChoiceActionListener;
import spellpad.eventhandlers.SpellcheckActionListener;
import spellpad.eventhandlers.mouse.MouseListener;
import spellpad.eventhandlers.textmodifying.*;
import spellpad.swing.autocomplete.WordCountCache;

/**
 * @author Jesse
 */
public class SpellPadSwing {

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final String CONTENT_TYPE = "text/html";
    public static WordCountCache wordCache;
    public static JFrame window;

    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO code application logic here
        DictionaryLoader loader = new DictionaryLoader();
        new Thread(loader).start();
        SpellPadSwing spellpad = new SpellPadSwing();
        spellpad.init();
        while(!loader.isExecutionComplete()){
            System.out.println("sleeping.");
            Thread.sleep(175);
        }
        wordCache.setDictionary(loader.getTree());
    }

    public void init() {
        UndoManager undoManager = new UndoManager();
        undoManager.setLimit(500);
        setLookAndFeel();
        window = initFrame();
        SpellPadEditorPane editPane = initTextPane();
        wordCache = new WordCountCache(editPane);
        JScrollPane textAreaScrollPane = addScrolling(editPane, window);
        JMenuBar menubar = initMenuBar(editPane, undoManager);
        JToolBar toolBar = initToolBar(editPane, undoManager);

        textAreaScrollPane.setDoubleBuffered(true);
        editPane.setDoubleBuffered(true);
        window.setJMenuBar(menubar);
        window.add(toolBar, BorderLayout.PAGE_START);

        editPane.getDocument().addUndoableEditListener(undoManager);
        editPane.getDocument().addDocumentListener(new DocumentChangedActionListener(editPane, wordCache));

        editPane.requestFocus();
        window.setVisible(true);
    }

    private void getAndRegisterOpenEventListener(SpellPadEditorPane editPane, JMenuItem open) {
        OpenEventActionListener openListener = new OpenEventActionListener(editPane);
        openListener.addResetable(wordCache);
        open.addActionListener(openListener);
    }

    private JToolBar initToolBar(SpellPadEditorPane editPane, UndoManager manager) {
        JToolBar toolBar = new JToolBar("Text Controls");
        JButton boldButton = new JButton("B");
        JButton italicButton = new JButton("I");
        JButton underlineButton = new JButton("U");
        JButton undoButton = new JButton("Undo");
        JButton redoButton = new JButton("Redo");
        JButton spellCheck = new JButton("Spellcheck");
        boldButton.addActionListener(new BoldActionListener(editPane));
        italicButton.addActionListener(new ItalicsActionListener(editPane));
        underlineButton.addActionListener(new UnderlineActionListener(editPane));
        undoButton.addActionListener(new UndoActionListener(manager));
        redoButton.addActionListener(new RedoActionListener(manager));
        spellCheck.addActionListener(new SpellcheckActionListener(editPane, wordCache, window));

        toolBar.add(boldButton);
        toolBar.add(italicButton);
        toolBar.add(underlineButton);
        toolBar.add(undoButton);
        toolBar.add(redoButton);
        toolBar.add(spellCheck);
        return toolBar;
    }

    private JMenuBar initMenuBar(SpellPadEditorPane editPane, UndoManager manager) {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem exit = new JMenuItem("Exit");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        getAndRegisterOpenEventListener(editPane, open);
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

        JMenu edit = new JMenu("Edit");
        JMenuItem bold = new JMenuItem("Bold");
        JMenuItem italic = new JMenuItem("Italic");
        JMenuItem underline = new JMenuItem("Underline");
        JMenuItem undo = new JMenuItem("Undo");
        JMenuItem redo = new JMenuItem("Redo");
        bold.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        italic.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        underline.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        bold.addActionListener(new BoldActionListener(editPane));
        italic.addActionListener(new ItalicsActionListener(editPane));
        underline.addActionListener(new UnderlineActionListener(editPane));
        undo.addActionListener(new UndoActionListener(manager));
        redo.addActionListener(new RedoActionListener(manager));

        edit.add(bold);
        edit.add(italic);
        edit.add(underline);
        edit.add(undo);
        edit.add(redo);
        menubar.add(edit);
        
        JMenu settings = new JMenu("Settings");
        JMenuItem spellCheckChoice = new JMenuItem("Dictionary");
        spellCheckChoice.addActionListener(new SpellCheckChoiceActionListener(spellCheckChoice));
        settings.add(spellCheckChoice);
        menubar.add(settings);

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
