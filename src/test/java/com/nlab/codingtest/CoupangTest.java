/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nlab.codingtest;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * 2020.02.12 Coding test of the COUPANG.
 *
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 *
 * Given nums = [2, 7, 11, 5], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 *
 * return [0, 1].
 *
 * @author Doohyun
 */
public class CoupangTest {

    private int[] nums;

    private int target;

    private int[] expectedResult;

    private static final int NOT_FOUND_INDEX = -1;

    @Before
    public void initializeUnit() {
        nums = new int[]{ 2, 7, 11, 5 };
        target = 9;
        expectedResult = new int[] {0, 1};
    }

    @Test
    public void testSolution1() {
        assertArrayEquals(expectedResult, findSolution1(nums, target));
    }

    /**
     * n^2 solution
     */
    private static int[] findSolution1(int[] nums, int target) {
        int size = nums.length;

        for (int i = 0; i < size; ++i) {
            for (int j = i + 1; j < size; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }

        return new int[]{ NOT_FOUND_INDEX, NOT_FOUND_INDEX };
    }

    @Test
    public void testSolution2() {
        assertArrayEquals(expectedResult, findSolution2(nums, target));
    }

    /**
     * 2*n solution
     */
    private static int[] findSolution2(int[] nums, int target) {
        int size = nums.length;

        Map<Integer, Integer> indexAndValueMapper = new HashMap<>();
        for (int i = 0; i < size; ++i) {
            indexAndValueMapper.put(nums[i], i);
        }

        for (int i = 0; i < size; ++i) {
            int value1 = nums[i];
            if (value1 < target) {
                int value2 = target - value1;
                if (indexAndValueMapper.containsKey(value2)) {
                    int value2Index = indexAndValueMapper.get(value2);
                    if (i != value2Index) {
                        return new int[] {i, value2Index};
                    }
                }
            }
        }

        return new int[]{ NOT_FOUND_INDEX, NOT_FOUND_INDEX };
    }

}
