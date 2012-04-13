/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spellpad.eventhandlers;

import java.awt.event.ActionEvent;
import java.io.*;
import javax.swing.JEditorPane;
import org.junit.*;
import static org.junit.Assert.*;
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
    }
    
    @After
    public void tearDown() {
        if (output.exists()) {
            output.delete();
        }
    }
    File output = new File("C:/text.txt");

    /**
     * Test of actionPerformed method, of class SaveEventActionListener.
     */
    @Test
    public void testActionPerformed() {
        System.out.println("saveActionPerformed-null-null");
        ActionEvent e = null;
        JEditorPane textArea = new JEditorPane();
        SaveEventActionListener instance = new SaveEventActionListener(null);
        String s = "This is some sample text.";
        textArea.setText(s);
        instance.actionPerformed(e);
        assertTrue(!output.exists());
    }

    /**
     * Test of actionPerformed method, of class SaveEventActionListener.
     */
    @Test
    public void testActionPerformed2() {
        System.out.println("saveActionPerformed-notnull-null");
        ActionEvent e = new ActionEvent(new Object(), 0, null);
        JEditorPane textArea = new JEditorPane();
        SaveEventActionListener instance = new SaveEventActionListener(null);
        String s = "This is some sample text.";
        textArea.setText(s);
        instance.actionPerformed(e);
        assertTrue(!output.exists());
    }

    /**
     * Test of actionPerformed method, of class SaveEventActionListener.
     */
    @Test
    public void testActionPerformed3() {
        System.out.println("saveActionPerformed-null-notnull");
        ActionEvent e = null;
        JEditorPane textArea = new JEditorPane();
        SaveEventActionListener instance = new SaveEventActionListener(textArea);
        String s = "This is some sample text.";
        textArea.setText(s);
        instance.actionPerformed(e);
        assertTrue(!output.exists());
    }

    /**
     * Test of actionPerformed method, of class SaveEventActionListener.
     */
    @Test
    public void testActionPerformed4() {
        System.out.println("saveActionPerformed-notnull-notnull");
        ActionEvent e = new ActionEvent(new Object(), 0, null);
        JEditorPane textArea = new JEditorPane();
        SaveEventActionListener instance = new SaveEventActionListener(null);
        String s = "This is some sample text.";
        textArea.setText(s);
        instance.actionPerformed(e);
        assertTrue(output.exists());
        try {
            StringBuilder fileContents = new StringBuilder();
            FileReader reader = new FileReader(output);
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
