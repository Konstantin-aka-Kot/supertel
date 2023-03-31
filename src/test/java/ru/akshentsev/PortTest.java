package ru.akshentsev;


import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PortTest {

    @Test
    public void testConvertIndexesToSequences() {
        String[] indexes = {"1,3-5", "2", "3-4"};
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1, 3, 4, 5),
                Arrays.asList(2),
                Arrays.asList(3, 4)
        );
        List<List<Integer>> actual = Port.convertIndexesToSequences(indexes);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAllUniqueOrderedGroups() {
        List<List<Integer>> sequences = Arrays.asList(
                Arrays.asList(1, 3, 4, 5),
                Arrays.asList(2),
                Arrays.asList(3, 4)
        );
        Set<List<Integer>> expected = Set.of(
                Arrays.asList(1, 2, 3),
                Arrays.asList(1, 2, 4),
                Arrays.asList(3, 2, 3),
                Arrays.asList(3, 2, 4),
                Arrays.asList(4, 2, 3),
                Arrays.asList(4, 2, 4),
                Arrays.asList(5, 2, 3),
                Arrays.asList(5, 2, 4)
        );
        Set<List<Integer>> actual = Port.getAllUniqueOrderedGroups(sequences);
        assertEquals(expected, actual);
    }
}
