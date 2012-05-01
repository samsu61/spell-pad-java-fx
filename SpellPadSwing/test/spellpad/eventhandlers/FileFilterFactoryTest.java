package spellpad.eventhandlers;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.*;

/**
 *
 * @author Jesse Allen
 */
public class FileFilterFactoryTest {

    public FileFilterFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        test = new File("c:/test.txt");
        if (test.exists()) {
            test.delete();
            test.createNewFile();
        }

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        if (test.exists()) {
            test.delete();
        }
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    static File test;

    /**
     * Test of getSpellpadFileFilter method, of class FileFilterFactory.
     */
    @Test
    public void testGetSpellpadFileFilter() {
        System.out.println("getSpellpadFileFilter");
        FileFilter expResult = new FileNameExtensionFilter("Text Files", "txt");
        FileFilter result = FileFilterFactory.getSpellpadFileFilter();
        assertEquals(expResult.accept(test), result.accept(test));
        assertEquals(expResult.getDescription(), result.getDescription());
    }

    /**
     * Test of getFileFromPopupDialogue method, of class FileFilterFactory.
     */
    @Test
    public void testGetFileFromPopupDialogue() {
        //SAVE
        System.out.println("getFileFromPopupDialogue - Save");
        FileAction openOrSave = FileAction.SAVE;
        FileChooserDetails expResult = new FileChooserDetails(test, new FileNameExtensionFilter("Text Files", "text"));
        FileChooserDetails result = FileFilterFactory.getFileFromPopupDialogue(openOrSave);
        assertEquals(expResult.getFile(), result.getFile());
        FileFilter expResultFilter = expResult.getExtensionFilter();
        FileFilter resultFilter = result.getExtensionFilter();
        assertTrue(resultFilter.accept(test));
        assertEquals(expResultFilter.getDescription(), resultFilter.getDescription());
        //OPEN
        System.out.println("getFileFromPopupDialogue - Open");
        openOrSave = FileAction.OPEN;
        //expResult is the same
        result = FileFilterFactory.getFileFromPopupDialogue(openOrSave);
        assertEquals(expResult.getFile(), result.getFile());
        resultFilter = result.getExtensionFilter();
        assertTrue(resultFilter.accept(test));
        assertEquals(expResultFilter.getDescription(), resultFilter.getDescription());

    }
}
