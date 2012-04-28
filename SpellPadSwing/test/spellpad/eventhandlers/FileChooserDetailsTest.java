package spellpad.eventhandlers;

import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import static org.junit.Assert.assertEquals;
import org.junit.*;

/**
 *
 * @author Jesse Allen
 */
public class FileChooserDetailsTest {

    public FileChooserDetailsTest() {
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
     * Test of getFile method, of class FileChooserDetails.
     */
    @Test
    public void testGetFile() {
        System.out.println("getFile");
        File expResult = new File("c:/this/is/a/test/path");
        FileChooserDetails instance = new FileChooserDetails(expResult, null);
        File result = instance.getFile();
        assertEquals(expResult, result);
    }

    /**
     * Test of getExtensionFilter method, of class FileChooserDetails.
     */
    @Test
    public void testGetExtensionFilter() {
        System.out.println("getExtensionFilter");
        FileNameExtensionFilter expResult = new FileNameExtensionFilter("A filter", "extension");
        FileChooserDetails instance = new FileChooserDetails(null, expResult);
        FileNameExtensionFilter result = instance.getExtensionFilter();
        assertEquals(expResult, result);
    }
}
