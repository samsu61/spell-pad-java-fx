package spellpad.eventhandlers;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.*;
import spellpad.filetype.parsing.SpellpadParser;

/**
 *
 * @author Jesse
 */
public class SaveEventActionListenerTest {

    public SaveEventActionListenerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        if (output.exists()) {
            output.delete();
        }

    }

    @After
    public void tearDown() {
        if (output.exists()) {
            output.delete();
        }

    }
    File output = new File("C:/test.txt");

    /**
     * Test of actionPerformed method, of class SaveEventActionListener.
     */
    @Test
    public void testActionPerformedNullInputs() {
        System.out.println("saveActionPerformed-null-null");
        ActionEvent e = null;
        JEditorPane textArea = new JTextPane();
        SaveEventActionListener instance = new SaveEventActionListener(null);
        String s = "This is some sample text.";
        textArea.setText(s);
        assertTrue(!output.exists());
        instance.actionPerformed(e);
        assertTrue(!output.exists());
    }

    /**
     * Test of actionPerformed method, of class SaveEventActionListener.
     */
    @Test
    public void testActionPerformed2EventNotNullTextPaneNull() {
        System.out.println("saveActionPerformed-notnull-null");
        ActionEvent e = new ActionEvent(new Object(), 0, null);
        JEditorPane textArea = new JTextPane();
        SaveEventActionListener instance = new SaveEventActionListener(null);
        String s = "This is some sample text.";
        textArea.setText(s);
        assertTrue(!output.exists());
        instance.actionPerformed(e);
        assertTrue(!output.exists());
        //      assertTrue(!output.exists());
    }

    /**
     * Test of actionPerformed method, of class SaveEventActionListener.
     */
    @Test
    public void testActionPerformedEventNullTextPaneNotNull() {
        System.out.println("saveActionPerformed-null-notnull");
        ActionEvent e = null;
        JEditorPane textArea = new JTextPane();
        SaveEventActionListener instance = new SaveEventActionListener(textArea);
        String s = "This is some sample text.";
        textArea.setText(s);
        assertTrue(!output.exists());
        instance.actionPerformed(e);
        assertTrue(output.exists());
        try (FileReader reader = new FileReader(output);) {
            StringBuilder fileContents = new StringBuilder();
            char[] fileCharacters = new char[(int) output.length()];
            reader.read(fileCharacters);
            fileContents.append(fileCharacters);
            String preppedText = SpellpadParser.prepPlainText(fileContents.toString());
            assertEquals(s, preppedText);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Test of actionPerformed method, of class SaveEventActionListener.
     */
    @Test
    public void testActionPerformedValidInputs() {
        System.out.println("saveActionPerformed-notnull-notnull");
        ActionEvent e = new ActionEvent(new Object(), 0, null);
        JEditorPane textArea = new JEditorPane();
        SaveEventActionListener instance = new SaveEventActionListener(textArea);
        String s = "This is some sample text.";
        textArea.setText(s);
        assertTrue(!output.exists());
        instance.actionPerformed(e);
        assertTrue(output.exists());
        try (FileReader reader = new FileReader(output)) {
            StringBuilder fileContents = new StringBuilder();
            char[] fileCharacters = new char[(int) output.length()];
            reader.read(fileCharacters);
            fileContents.append(fileCharacters);
            String preppedText = SpellpadParser.prepPlainText(fileContents.toString());
            assertEquals(s, preppedText);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
