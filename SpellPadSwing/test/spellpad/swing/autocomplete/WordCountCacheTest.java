package spellpad.swing.autocomplete;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JTextPane;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.*;

/**
 *
 * @author Jesse Allen
 */
public class WordCountCacheTest {

    public WordCountCacheTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        textPane = new JTextPane();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        textPane.setText("them them then their their their cheese their them");
    }

    @After
    public void tearDown() {
        textPane.setText("");
    }
    static JTextPane textPane;

    /**
     * Test of getWordCount method, of class WordCountCache.
     */
    @Test
    public void testGetWordCount() {
        System.out.println("getWordCount");
        WordCountCache instance = new WordCountCache(textPane);
        instance.run();
        ArrayList expResult = new ArrayList<Entry>();
        expResult.add(new Entry(3, "them"));
        expResult.add(new Entry(1, "then"));
        expResult.add(new Entry(4, "their"));
        expResult.add(new Entry(1, "cheese"));
        Collections.sort(expResult);
        ArrayList result = instance.getWordCount();
        for (int i = 0; i < result.size(); i++) {
            assertEquals(expResult.get(i), result.get(i));
        }

    }

    /**
     * Test of getAutocompleteTree method, of class WordCountCache.
     */
    @Test
    public void testGetAutocompleteTree() {
        System.out.println("getAutocompleteTree");
        WordCountCache instance = new WordCountCache(textPane);
        instance.run();
        TernarySearchTree expResult = new TernarySearchTree();
        expResult.add("them");
        expResult.add("them");
        expResult.add("then");
        expResult.add("their");
        expResult.add("their");
        expResult.add("their");
        expResult.add("cheese");
        expResult.add("their");
        expResult.add("them");
        TernarySearchTree result = instance.getAutocompleteTree();
        assertTrue(result.equals(expResult));


    }

    /**
     * Test of reset method, of class WordCountCache.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        WordCountCache instance = new WordCountCache(textPane);
        instance.reset();
        ArrayList<Entry> expectedA = new ArrayList<>();
        TernarySearchTree expectedB = new TernarySearchTree();
        ArrayList<Entry> resultA = new ArrayList<>();
        TernarySearchTree resultB = new TernarySearchTree();
        assertEquals(expectedA, resultA);
        assertEquals(expectedB, resultB);

    }
//    /**
//     * Test of run method, of class WordCountCache.
//     */
//    @Test
//    public void testRun() {
//        System.out.println("run");
//        WordCountCache instance = new WordCountCache(textPane);
//        instance.run();
//        
//        
//    }
}
