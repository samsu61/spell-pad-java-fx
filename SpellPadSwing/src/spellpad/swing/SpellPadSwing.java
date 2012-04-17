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
import spellpad.eventhandlers.OpenEventActionListener;
import spellpad.eventhandlers.SaveEventActionListener;
import spellpad.eventhandlers.mouse.MouseListener;

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
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFrame window = initFrame();

        JEditorPane editPane = new JEditorPane();
        HTMLDocument document = new HTMLDocument();
        document.setParser(new ParserDelegator());

        editPane.setDocument(document);
        editPane.setContentType(CONTENT_TYPE);
        JScrollPane textAreaScrollPane = new JScrollPane(editPane);
        editPane.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        editPane.addMouseListener(new MouseListener(editPane));
        window.add(textAreaScrollPane, BorderLayout.CENTER);

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

        textAreaScrollPane.setDoubleBuffered(true);
        editPane.setDoubleBuffered(true);
        window.setJMenuBar(menubar);
        
        

        window.setVisible(true);
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
