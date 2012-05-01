/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spellpad.swing.autocomplete;

import java.util.ArrayList;
import java.util.Random;
import static org.junit.Assert.assertTrue;
import org.junit.*;

/**
 *
 * @author Jesse Allen
 */
public class TernarySearchTreeTest {

    public TernarySearchTreeTest() {
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
     * Test of add method, of class TernarySearchTree.
     */
    @Test
    public void testAddAndContains() {
        System.out.println("add");
        TernarySearchTree instance = new TernarySearchTree();
        ArrayList<String> strings = randomStrings();
        for (String s : strings) {
            instance.add(s);
        }
        for (String s : strings) {
            assertTrue(instance.contains(s));
        }

    }

    private ArrayList<String> randomStrings() {
        ArrayList<String> strings = new ArrayList<String>();
        Random gen = new Random();
        for (int i = 0; i < 1000000; i++) {
            StringBuilder s = new StringBuilder();
            int thisLength = gen.nextInt(15) + 4;
            for (int j = 0; j < thisLength; j++) {
                int random = gen.nextInt(256);
                char c = (char) random;
                s.append(c);
            }
            strings.add(s.toString());
        }
        return strings;
    }

//    /**
//     * Test of contains method, of class TernarySearchTree.
//     */
//    @Test
//    public void testContains() {
//        System.out.println("contains");
//        String s = "";
//        TernarySearchTree instance = new TernarySearchTree();
//        boolean expResult = false;
//        boolean result = instance.contains(s);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of search method, of class TernarySearchTree.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        Random gen = new Random();
        TernarySearchTree instance = new TernarySearchTree();
        ArrayList<String> strings = randomStrings();
        for (String s : strings) {
            instance.add(s);
        }
        for (String s : strings) {
            int length = s.length();
            if (length == 4) {
                continue;
            }
            int substringTo = gen.nextInt(length - 1);
            if (substringTo == 0) {
                continue;
            }
            String searchFor = s.substring(0, substringTo);
            String suffix = instance.search(searchFor);
            assertTrue(strings.contains(searchFor + suffix));
        }

    }

    /**
     * Test of reset method, of class TernarySearchTree.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        TernarySearchTree instance = new TernarySearchTree();
        ArrayList<String> strings = randomStrings();
        for (String s : strings) {
            instance.add(s);
        }
        instance.reset();
        for (String s : strings) {
            assertTrue(!instance.contains(s));
        }
    }
}
