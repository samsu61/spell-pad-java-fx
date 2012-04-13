/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spellpad.eventhandlers;

import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Jesse
 */
public class OpenEventActionListenerTest {
    
    public OpenEventActionListenerTest() {
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
    }

    /**
     * Test of actionPerformed method, of class OpenEventActionListener.
     */
    @Test
    public void testActionPerformed() {
        System.out.println("openactionPerformed-null-null");
        ActionEvent e = null;
        OpenEventActionListener instance = new OpenEventActionListener(null);
        instance.actionPerformed(e);
    }
    
    @Test
    public void testActionPerformed2() {
         System.out.println("actionPerformed-notnull-null");
        ActionEvent e = new ActionEvent(new Object(), 0, null);
        OpenEventActionListener instance = new OpenEventActionListener(null);
        instance.actionPerformed(e);
    }
    
    @Test
    public void testActionPerformed3() {
         System.out.println("actionPerformed-null-notnull");
        ActionEvent e = null;
        JEditorPane textPane = null;
        textPane = new JEditorPane();
        OpenEventActionListener instance = 
                new OpenEventActionListener(textPane);
        String textBefore = textPane.getText();
        instance.actionPerformed(e);
        assertEquals(textPane.getText(), textBefore);
    }
    
    @Test
    public void testActionPerformed4() {
         System.out.println("actionPerformed-notnull-notnull");
        ActionEvent e = new ActionEvent(new Object(), 0, null);
        JEditorPane textPane = new JEditorPane();
        String textBefore = textPane.getText();
        OpenEventActionListener instance = new OpenEventActionListener(textPane);
        instance.actionPerformed(e);
        assertNotSame(textPane.getText(), textBefore);
    }
}
