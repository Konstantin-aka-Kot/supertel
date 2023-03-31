package ru.akshentsev;

import java.util.*;
import java.util.stream.Collectors;

public class Port {
    public static void main( String[] args) {
        String[] indexes = {"1,3-5", "2", "3-4"};
        List<List<Integer>> sequences = convertIndexesToSequences(indexes);
        Set<List<Integer>> uniqueGroups = getAllUniqueOrderedGroups(sequences);

        uniqueGroups.forEach(group -> System.out.println(group));
    }

    public static List<List<Integer>> convertIndexesToSequences(String[] indexes) {
        return Arrays.stream(indexes)
                .map(Port::convertStringToSequence)
                .collect(Collectors.toList());
    }

    public static Set<List<Integer>> getAllUniqueOrderedGroups(List<List<Integer>> sequences) {
        Set<List<Integer>> uniqueGroups = new LinkedHashSet<>();
        generateOrderedGroups(uniqueGroups, sequences, 0, new ArrayList<>());
        return uniqueGroups;
    }

    private static List<Integer> convertStringToSequence(String index) {
        List<Integer> sequence = new ArrayList<>();
        String[] parts = index.split(",");

        for (String part : parts) {
            if (part.contains("-")) {
                String[] range = part.split("-");
                int start = Integer.parseInt(range[0]);
                int end = Integer.parseInt(range[1]);

                for (int i = start; i <= end; i++) {
                    sequence.add(i);
                }
            } else {
                sequence.add(Integer.parseInt(part));
            }
        }

        return sequence;
    }

    private static void generateOrderedGroups(Set<List<Integer>> uniqueGroups, List<List<Integer>> sequences, int depth, List<Integer> currentGroup) {
        if (depth == sequences.size()) {
            uniqueGroups.add(new ArrayList<>(currentGroup));
            return;
        }

        for (Integer value : sequences.get(depth)) {
            currentGroup.add(value);
            generateOrderedGroups(uniqueGroups, sequences, depth + 1, currentGroup);
            currentGroup.remove(currentGroup.size() - 1);
        }
    }
}
