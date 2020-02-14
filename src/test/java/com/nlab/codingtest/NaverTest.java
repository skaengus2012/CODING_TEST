package com.nlab.codingtest;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 2018.03.04 Coding test of the Naver
 *
 * @author Doohyun
 */
public class NaverTest {

    /**
     * The specified array exists.
     * Shuffle the array and print the result.
     */
    @Test
    public void printShuffleResult() {
        // The specified array
        List<String> targets = new ArrayList<>();
        targets.add("Hello1");
        targets.add("Hello2");
        targets.add("Hello3");
        targets.add("Hello4");
        targets.add("Hello5");
        targets.add("Hello6");

        // Solution
        List<Integer> indexPull = IntStream.range(0, targets.size())
                .boxed()
                .collect(Collectors.toList());

        Map<Integer, String> shuffleMap = new TreeMap<>();
        for (String data : targets) {
            Random rand = new Random();
            int randValue = rand.nextInt(indexPull.size());
            int shuffleIndex = indexPull.get(randValue);
            indexPull.remove(randValue);

            shuffleMap.put(shuffleIndex, data);
        }

        targets.clear();
        targets.addAll(shuffleMap.values());
        targets.forEach(System.out::println);
    }

    /**
     * The specified array exists.
     * Find duplicate values ​​and print the result.
     */
    @Test
    public void printDuplicateField() {
        // The specified array
        List<String> targets = new ArrayList<>();
        targets.add("Hello1");
        targets.add("Hello2");
        targets.add("Hello1");
        targets.add("Hello5");
        targets.add("Hello5");
        targets.add("Hello8");

        // solution
        List<String> results = new ArrayList<>();
        Set<String> duplicates = new HashSet<>();

        for (String data : targets) {
            boolean isContains = duplicates.contains(data);
            if (isContains) {
                results.add(data);
            } else {
                duplicates.add(data);
            }
        }

        results.forEach(System.out::println);
    }

    /**
     * The specified tree exists.
     * Find the maximum depth and print the result.
     */
    @Test
    public void printMaxDepthInTree() {
        // The specified tree
        CompositeVO root = makeTree();

        // solution
        int maxDeath = getDeathByRecursive(root, 0);
        System.out.println(maxDeath);
    }

    private CompositeVO makeTree() {
        CompositeVO root = new CompositeVO("Root");
        CompositeVO level1_A = new CompositeVO("A");

        CompositeVO level1_B = new CompositeVO("B");
        {
            CompositeVO level2_E = new CompositeVO("E");
            {
                CompositeVO level3_G = new CompositeVO("G");
                level2_E.addChild(level3_G);
            }

            CompositeVO level2_F = new CompositeVO("F");

            level1_B.addChild(level2_E);
            level1_B.addChild(level2_F);
        }

        CompositeVO level1_C = new CompositeVO("C");
        {
            CompositeVO level2_H = new CompositeVO("H");
            CompositeVO level2_I = new CompositeVO("I");

            level1_C.addChild(level2_H);
            level1_C.addChild(level2_I);
        }

        root.addChild(level1_A);
        root.addChild(level1_B);
        root.addChild(level1_C);

        return root;
    }

    private static int getDeathByRecursive(CompositeVO composite, int currentDeath) {
        if (composite.getChildCount() == 0) {
            return currentDeath;
        } else {
            int maxDeath = 0;

            for (CompositeVO child : composite.getChildren()) {
                int cirDeath = getDeathByRecursive(child, currentDeath + 1);

                if (cirDeath > maxDeath) {
                    maxDeath = cirDeath;
                }
            }

            return maxDeath;
        }
    }

    public static class CompositeVO {
        private String name;
        private List<CompositeVO> children = new ArrayList<>();

        public CompositeVO(String name) {
            this.name = name;
        }

        // Use this field if you need to do a print test.
        public String getName() {
            return name;
        }

        public List<CompositeVO> getChildren() {
            return children;
        }

        public void addChild(CompositeVO compositeVO) {
            children.add(compositeVO);
        }

        public int getChildCount() {
            return children.size();
        }
    }
}
