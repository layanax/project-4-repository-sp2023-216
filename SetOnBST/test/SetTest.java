import static org.junit.Assert.assertEquals;

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

    //constructor test

    @Test
    public void testConstructorRoutine() {
        Set<String> set = this.constructorTest();
        Set<String> expected = this.constructorRef();

        assertEquals(expected, set);

    }

    //add tests

    //test adding to empty
    @Test
    public void testAddEdgeCase() {
        Set<String> set = this.createFromArgsTest();
        Set<String> expected = this.createFromArgsRef("A");
        set.add("A");

        assertEquals(expected, set);
    }

    //test adding with only 1 other item in the tree
    @Test
    public void testAddSpecialCase() {
        Set<String> set = this.createFromArgsTest("B");
        Set<String> expected = this.createFromArgsRef("A", "B");
        set.add("A");

        assertEquals(expected, set);
    }

    //test adding with only left trees
    @Test
    public void testAddRoutinCase1() {
        Set<String> set = this.createFromArgsTest("D", "C", "B");
        Set<String> expected = this.createFromArgsRef("D", "C", "B", "A");
        set.add("A");

        assertEquals(expected, set);
    }

    //test adding with only right trees
    @Test
    public void testAddRoutinCase2() {
        Set<String> set = this.createFromArgsTest("A", "B", "C");
        Set<String> expected = this.createFromArgsRef("A", "B", "C", "D");
        set.add("D");

        assertEquals(expected, set);
    }

    //test adding with both left and right
    @Test
    public void testAddRoutinCase3() {
        Set<String> set = this.createFromArgsTest("D", "B", "K", "A", "C", "F");
        Set<String> expected = this.createFromArgsTest("D", "B", "K", "A", "C",
                "F", "E");
        set.add("E");

        assertEquals(expected, set);
    }

    //remove tests

    @Test
    //removing to an empty set
    public void testRemoveEdgeCase() {
        Set<String> set = this.createFromArgsTest("A");
        Set<String> expected = this.createFromArgsRef();
        String temp = set.remove("A");

        assertEquals(expected, set);
        assertEquals("A", temp);
    }

    @Test
    //removing root node
    public void testRemoveSpecial() {
        Set<String> set = this.createFromArgsTest("C", "B", "A", "D");
        Set<String> expected = this.createFromArgsRef("B", "A", "D");
        String temp = set.remove("C");

        assertEquals(expected, set);
        assertEquals("C", temp);
    }

    @Test
    //removing to non empty with both left and right subtree from end
    public void testRemoveRoutineCase() {
        Set<String> set = this.createFromArgsTest("D", "B", "K", "A", "C", "F",
                "L", "E");
        Set<String> expected = this.createFromArgsRef("D", "B", "K", "A", "F",
                "L", "E");
        String temp = set.remove("C");

        assertEquals(expected, set);
        assertEquals("C", temp);
    }

    //removing to non empty with both left and right subtree from middle
    @Test
    public void testRemoveRoutineCase2() {
        Set<String> set = this.createFromArgsTest("D", "B", "K", "A", "C", "F",
                "L", "E");
        Set<String> expected = this.createFromArgsRef("D", "B", "C", "A", "F",
                "L", "E");
        String temp = set.remove("K");

        assertEquals(expected, set);
        assertEquals("K", temp);
    }

    //removing to non empty with only left from end
    @Test
    public void testRemoveRoutineCase3() {
        Set<String> set = this.createFromArgsTest("D", "C", "B", "A");
        Set<String> expected = this.createFromArgsRef("D", "C", "B");
        String temp = set.remove("A");

        assertEquals(expected, set);
        assertEquals("A", temp);
    }

    //removing to non empty with only left from middle
    @Test
    public void testRemoveRoutineCase4() {
        Set<String> set = this.createFromArgsTest("D", "C", "B", "A");
        Set<String> expected = this.createFromArgsRef("D", "C", "A");
        String temp = set.remove("B");

        assertEquals(expected, set);
        assertEquals("B", temp);
    }

    //removing to non empty with only right from end
    @Test
    public void testRemoveRoutineCase5() {
        Set<String> set = this.createFromArgsTest("A", "B", "C", "D");
        Set<String> expected = this.createFromArgsRef("A", "B", "C");
        String temp = set.remove("D");

        assertEquals(expected, set);
        assertEquals("D", temp);
    }

    //removing to non empty with only right from middle
    @Test
    public void testRemoveRoutineCase6() {
        Set<String> set = this.createFromArgsTest("A", "B", "C", "D");
        Set<String> expected = this.createFromArgsRef("A", "C", "D");
        String temp = set.remove("B");

        assertEquals(expected, set);
        assertEquals("B", temp);
    }

    //removeAny tests

    @Test
    public void testRemoveAnyEdgeCase() {
        Set<String> set = this.createFromArgsTest("A");
        Set<String> expected = this.createFromArgsRef();
        String temp = set.removeAny();

        assertEquals(expected, set);
        assertEquals(temp, "A");

    }

    @Test
    public void testRemoveAnySpecialCase() {
        Set<String> set = this.createFromArgsTest("A", "B");
        Set<String> expected = this.createFromArgsRef("B");
        String temp = set.removeAny();

        assertEquals(expected, set);
        assertEquals("A", temp);
    }

    @Test
    public void testRemoveAnyRoutineCase() {
        Set<String> set = this.createFromArgsTest("C", "A", "B");
        Set<String> expected = this.createFromArgsRef("C", "B");
        String temp = set.removeAny();

        assertEquals(expected, set);
        assertEquals("A", temp);

    }

    //contains tests
    @Test
    public void testContainsaEdgeCase() {
        Set<String> set = this.createFromArgsTest();
        Set<String> expected = this.createFromArgsRef();

        assertEquals(false, set.contains("great"));
        assertEquals(expected, set);
    }

    public void testContainsSpecialCase1() {
        Set<String> set = this.createFromArgsTest("A");
        Set<String> expected = this.createFromArgsRef("A");

        assertEquals(true, set.contains("A"));
        assertEquals(expected, set);
    }

    public void testContainsSpecialCase2() {
        Set<String> set = this.createFromArgsTest("A");
        Set<String> expected = this.createFromArgsRef("A");

        assertEquals(false, set.contains("B"));
        assertEquals(expected, set);
    }

    public void testContainsNormalCase() {
        Set<String> set = this.createFromArgsTest("A", "C");
        Set<String> expected = this.createFromArgsRef("A", "C");

        assertEquals(false, set.contains("B"));
        assertEquals(expected, set);
    }

    public void testContainsNormalCase2() {
        Set<String> set = this.createFromArgsTest("A", "B", "C");
        Set<String> expected = this.createFromArgsRef("A", "B", "C");

        assertEquals(true, set.contains("B"));
        assertEquals(expected, set);
    }

    //size tests

    @Test
    public void testSizeEdgeCase() {
        Set<String> set = this.createFromArgsTest();
        Set<String> expected = this.createFromArgsRef();

        assertEquals(0, set.size());
        assertEquals(expected, set);
    }

    @Test
    public void testSizeSpecialCase() {
        Set<String> set = this.createFromArgsTest("A");
        Set<String> expected = this.createFromArgsRef("A");

        assertEquals(1, set.size());
        assertEquals(expected, set);
    }

    @Test
    public void testSizeRoutineCase() {
        Set<String> set = this.createFromArgsTest("A", "B");
        Set<String> expected = this.createFromArgsRef("A", "B");

        assertEquals(2, set.size());
        assertEquals(expected, set);
    }

}
