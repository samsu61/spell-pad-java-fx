package spellpad.eventhandlers;

import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.*;

/**
 *
 * @author Jesse Allen
 */
public class ClipboardHandlerTest {

    public ClipboardHandlerTest() {
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
     * Test of lostOwnership method, of class ClipboardHandler.
     */
    @Test
    public void testLostOwnership() {
        //Method does nothing.
        System.out.println("lostOwnership");
        Clipboard clipboard = null;
        Transferable contents = null;
        ClipboardHandler instance = new ClipboardHandler();
        instance.lostOwnership(clipboard, contents);
        assertTrue(clipboard == null);
        assertTrue(clipboard == null);
    }

    /**
     * Test of setClipboardContents method, of class ClipboardHandler.
     */
    @Test
    public void testSetClipboardContents() throws UnsupportedFlavorException, IOException {
        System.out.println("setClipboardContents");
        String selected = "Content Selected.";
        ClipboardHandler instance = new ClipboardHandler();
        instance.setClipboardContents(selected);
        assert (Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor).equals(selected));
    }

    /**
     * Test of getClipboardContents method, of class ClipboardHandler.
     */
    @Test
    public void testGetClipboardContents() {
        System.out.println("getClipboardContents");
        ClipboardHandler instance = new ClipboardHandler();
        String expResult = "This is expected data";
        StringSelection selection = new StringSelection(expResult);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, instance);
        String result = instance.getClipboardContents();
        assertEquals(expResult, result);
    }
}
