package spellpad.filetype.parsing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.*;

/**
 *
 * @author Jesse
 */
public class SpellpadParserTest {
    
    public SpellpadParserTest() {
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
     * Test of prepPlainText method, of class SpellpadParser.
     */
    @Test
    public void testPrepPlainText() {
        System.out.println("prepPlainText");
        String plainText = "";
        String expResult = "";
        String result = SpellpadParser.prepPlainText(plainText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of restorePlainText method, of class SpellpadParser.
     */
    @Test
    public void testRestorePlainText() {
        System.out.println("restorePlainText");
        String htmlText = "";
        String expResult = "";
        String result = SpellpadParser.restorePlainText(htmlText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toHTML method, of class SpellpadParser.
     */
    @Test
    public void testToHTML() {
        System.out.println("toHTML");
        String spellpadText = "";
        String expResult = "";
        String result = SpellpadParser.toHTML(spellpadText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toSpellPad method, of class SpellpadParser.
     */
    @Test
    public void testToSpellPad() {
        System.out.println("toSpellPad");
        String htmlText = "";
        String expResult = "";
        String result = SpellpadParser.toSpellPad(htmlText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
