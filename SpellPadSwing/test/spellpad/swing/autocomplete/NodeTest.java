/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spellpad.swing.autocomplete;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Jesse Allen
 */
public class NodeTest {
    
    public NodeTest() {
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
     * Test of increasePopularity method, of class Node.
     */
    @Test
    public void testIncreasePopularity() {
        System.out.println("increasePopularity");
        Node instance = new Node(' ', false);
        instance.increasePopularity();
        instance.increasePopularity();
        instance.increasePopularity();
        instance.increasePopularity();
        instance.increasePopularity();
        instance.increasePopularity();
        instance.increasePopularity();
        instance.increasePopularity();
        instance.increasePopularity();
        instance.increasePopularity();
        assertEquals(10, instance.getPopularity());
        
    }

    /**
     * Test of getLeftChild method, of class Node.
     */
    @Test
    public void testGetLeftChild() {
        System.out.println("getLeftChild");
        Node instance = new Node('m', false);
        Node expResult = new Node('l', false);
        instance.setLeftChild(expResult);
        Node result = instance.getLeftChild();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMiddleChild method, of class Node.
     */
    @Test
    public void testGetMiddleChild() {
        System.out.println("getMiddleChild");
        Node instance = new Node('c', false);
        Node expResult = new Node('m', false);
        instance.setMiddleChild(expResult);
        Node result = instance.getMiddleChild();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMyChar method, of class Node.
     */
    @Test
    public void testGetMyChar() {
        System.out.println("getMyChar");
        Node instance = new Node(' ', false);
        char expResult = ' ';
        char result = instance.getMyChar();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRightChild method, of class Node.
     */
    @Test
    public void testGetRightChild() {
        System.out.println("getRightChild");
        Node instance = new Node('c', false);
        Node expResult = new Node('r', false);
        instance.setRightChild(expResult);
        Node result = instance.getRightChild();
        assertEquals(expResult, result);
    }

    /**
     * Test of isWordEnd method, of class Node.
     */
    @Test
    public void testIsWordEnd() {
        System.out.println("isWordEnd");
        Node instance = new Node('c', true);
        boolean result = instance.isWordEnd();
        assertTrue(result);
        
    }

    /**
     * Test of setWordEnd method, of class Node.
     */
    @Test
    public void testSetWordEnd() {
        System.out.println("setWordEnd");
        boolean wordEnd = true;
        Node instance = new Node('c', false);
        instance.setWordEnd(wordEnd);
        assertTrue(instance.isWordEnd());
    }

    /**
     * Test of getPopularity method, of class Node.
     */
    @Test
    public void testGetPopularity() {
        System.out.println("getPopularity");
        Node instance = new Node(' ', false);
        int expResult = 0;
        int result = instance.getPopularity();
        assertEquals(expResult, result);
    }

}
