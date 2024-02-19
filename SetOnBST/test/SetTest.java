import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Layan Abdallah & Oak Hodous
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    //constructor tests

    @Test
    public void testConstructorEdgeCase() {
        Set<String> set = this.constructorTest();
        assertTrue(set.size() == 0);
    }

    @Test
    public void testConstructorSpecialCase() {
        Set<String> set = this.constructorTest();
        set.add("A");
        assertFalse(set.size() == 0);
    }

    @Test
    public void testConstructorRoutineCase() {
        Set<String> set = this.constructorTest();
        set.add("A");
        set.add("B");
        assertEquals(2, set.size());
    }

    //add tests

    @Test
    public void testAddEdgeCase() {
        Set<String> set = this.constructorTest();
        assertFalse(set.contains("A"));
    }

    @Test
    public void testAddSpecialCase() {
        Set<String> set = this.constructorTest();
        set.add("A");
        assertTrue(set.contains("A"));
    }

    @Test
    public void testAddRoutineCase() {
        Set<String> set = this.constructorTest();
        set.add("A");
        set.add("B");
        assertEquals(2, set.size());
    }

    //remove tests

    @Test
    public void testRemoveEdgeCase() {
        Set<String> set = this.constructorTest();
        assertNull(set.remove("A"));
    }

    @Test
    public void testRemoveSpecialCase() {
        Set<String> set = this.constructorTest();
        set.add("A");
        set.add("B");
        assertEquals("B", set.remove("B"));
        assertFalse(set.contains("B"));
    }

    @Test
    public void testRemoveRoutineCase() {
        Set<String> set = this.constructorTest();
        set.add("A");
        set.add("B");
        set.add("C");
        assertEquals("B", set.remove("B"));
    }

    //removeAny tests

    @Test
    public void testRemoveAnyEdgeCase() {
        Set<String> set = this.constructorTest();
        assertNull(set.removeAny());
    }

    @Test
    public void testRemoveAnySpecialCase() {
        Set<String> set = this.constructorTest();
        set.add("A");
        set.add("B");
        assertNotNull(set.removeAny());
    }

    @Test
    public void testRemoveAnyRoutineCase() {
        Set<String> set = this.constructorTest();
        set.add("A");
        set.add("B");
        set.add("C");
        assertNotNull(set.removeAny());
    }

    //contains tests

    @Test
    public void testContainsEdgeCase() {
        Set<String> set = this.constructorTest();
        assertFalse(set.contains("A"));
    }

    @Test
    public void testContainsSpecialCase() {
        Set<String> set = this.constructorTest();
        set.add("A");
        assertTrue(set.contains("A"));
    }

    @Test
    public void testContainsRoutineCase() {
        Set<String> set = this.constructorTest();
        set.add("A");
        set.add("B");
        assertTrue(set.contains("B"));
    }

    //size tests

    @Test
    public void testSizeEdgeCase() {
        Set<String> set = this.constructorTest();
        assertEquals(0, set.size());
    }

    @Test
    public void testSizeSpecialCase() {
        Set<String> set = this.constructorTest();
        set.add("A");
        assertEquals(1, set.size());
    }

    @Test
    public void testSizeRoutineCase() {
        Set<String> set = this.constructorTest();
        set.add("A");
        set.add("B");
        assertEquals(2, set.size());
    }

    //isInTree tests

    //insertInTree tests

    //removeSmallest tests

    //removeFromTree tests

    //createNewRep tests

}
