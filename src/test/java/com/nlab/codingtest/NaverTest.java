package com.nlab.codingtest;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *  2018. 3. 4..
 *
 * @author Doohyun
 */
public class NaverTest {

    /**
     * 지정된 배열이 존재한다.
     * 해당 배열을 셔플하고, 결과를 출력하라.
     */
    @Test
    public void printShuffleResult() {
        // 해당 배열을 섞어야함
        List<String> targets = new ArrayList<>();
        targets.add("Hello1");
        targets.add("Hello2");
        targets.add("Hello3");
        targets.add("Hello4");
        targets.add("Hello5");
        targets.add("Hello6");

        // 문제 풀이
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
     * 지정된 배열이 존재한다.
     * 중복된 값을 찾아 결과를 출력하라.
     */
    @Test
    public void printDuplicateField() {
        // 해당 배열에서 중복 컬럼을 찾아야함.
        List<String> targets = new ArrayList<>();
        targets.add("Hello1");
        targets.add("Hello2");
        targets.add("Hello1");
        targets.add("Hello5");
        targets.add("Hello5");
        targets.add("Hello8");

        // 문제 풀이
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
     * Tree 가 주어져있을 때 최대 깊이를 구하고, 그 결과를 출력하라.
     */
    @Test
    public void printMaxDepthInTree() {
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


        // 문제 풀이.
        int maxDeath = getDeathByRecursive(root, 0);
        System.out.println(maxDeath);
    }

    /**
     * 깊이를 구하는 알고리즘 정의.
     */
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

    /**
     * 상속 관계 구조를 정의하기 위한 정보 정의.
     */
    public static class CompositeVO {
        private String name;
        private List<CompositeVO> children = new ArrayList<>();

        public CompositeVO(String name) {
            this.name = name;
        }

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
