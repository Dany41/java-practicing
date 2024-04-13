package org.effectivejava;

import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {

    @SneakyThrows
    public static void main(String[] args) {
//        ServiceLoader.load(Item.class).stream()
//                .map(ServiceLoader.Provider::get)
//                .filter(impl -> impl.getChapter().equals(Chapter.CHAPTER_11))
//                .filter(impl -> impl.getTheme().equals("Prefer concurrency utilities to wait and notify"))
//                .forEach(ItemUtils::printItemDetails);


        System.out.println(rps("scissors", "paper"));
        System.out.println(rps("scissors", "rock"));
        System.out.println(rps("scissors", "scissors"));
        System.out.println(rps("paper", "rock"));
        System.out.println(rps("paper", "scissors"));
        System.out.println(rps("paper", "paper"));
        System.out.println(rps("rock", "scissors"));
        System.out.println(rps("rock", "paper"));
        System.out.println(rps("rock", "rock"));
    }


    public static String rps(String p1, String p2) {
        return ITEM.beatMap.get(p1).get(p2);
    }

    enum ITEM {
        SCISSORS("paper"), PAPER("rock"), ROCK("scissors");


        private final String beat;

        ITEM(String beat) {
            this.beat = beat;
        }

        public final static Map<String, Map<String, String>> beatMap = Arrays.stream(ITEM.values())
                .collect(Collectors.toMap(
                        item -> item.name().toLowerCase(),
                        item -> Arrays.stream(ITEM.values())
                                .collect(Collectors.toMap(
                                        innerItem -> innerItem.name().toLowerCase(),
                                        innerItem -> {
                                            if (item.equals(innerItem)) {
                                                return "Draw";
                                            } else if (item.beat.equals(innerItem.name().toLowerCase())) {
                                                return "Player 1 won!";
                                            } else {
                                                return "Player 2 won!";
                                            }
                                        }))));
    }

    enum BEAT {
        BEAT_SCISSORS, BEAT_PAPER, BEAT_ROCK;
    }

    public static long countSubarrays(int[] nums, int k) {
        if (nums.length < k || nums.length == 0 || k == 0) return 0;
        int maxValue = getMaxFromArray(nums);

        long res = 0;
        long interimRes = 0;

        int i = 0;
        int j = 1;

        long maxCount = 0;
        long interimMaxCount = 0;
        int interimFirstMaxMet = -1;

        while (i < nums.length) {
            if (nums[i] == maxValue) {
                maxCount++;
            }
            if (maxCount >= k) res++;
            while (j < nums.length) {
                if (nums[j] == maxValue && i != j) {
                    maxCount++;
                    interimMaxCount++;
                    if (interimFirstMaxMet == -1) {
                        interimFirstMaxMet = j;
                    }
                }
                if (maxCount >= k && i != j) {
                    res++;
                    interimRes++;
                }
                j++;
            }
            if (nums[i] == maxValue) {
                maxCount--;
            }
            if (interimFirstMaxMet != -1 && maxCount < k) {
                i = interimFirstMaxMet;
                res += interimRes;
            } else {
                i++;
                maxCount -= interimMaxCount;
                interimMaxCount = 0;
                j = i;
                interimRes = 0;
            }
            interimFirstMaxMet = -1;
        }

        return res;
    }

    private static int getMaxFromArray(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) max = nums[i];
        }
        return max;
    }


}