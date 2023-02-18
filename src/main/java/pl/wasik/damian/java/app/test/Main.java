package org.test;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int simpleArraySum(List<Integer> ar) {
        int sum = 0;
        for (int i = 0; i < ar.size(); i++) {
            sum += ar.get(i);
        }
        System.out.println(sum);
        return sum;
    }

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(10);

        simpleArraySum(numbers);
    }
}
