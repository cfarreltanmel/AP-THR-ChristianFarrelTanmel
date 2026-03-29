package Soal1;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;

public class Soal1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter pairs of words and months (Separate by spaces):");
        String input = scanner.nextLine();
        String[] result = sort(input);
        System.out.println(Arrays.toString(result));
        scanner.close();
    }
    public static String[] sort(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new String[0];
        }
        String[] words = input.trim().split("\\s+");
        Stack<String> inputStack = new Stack<>();
        for (String word : words) {
            inputStack.push(word);
        }
        List<Pair> pairs = new ArrayList<>();
        if (inputStack.size() % 2 != 0) {
            throw new IllegalArgumentException("The input must contain pairs of words and months. Found an odd number of elements.");
        }
        while (!inputStack.isEmpty()) {
            String month = inputStack.pop();
            String word = inputStack.pop();
            pairs.add(new Pair(word, month));
        }
        pairs.sort(Comparator.comparingInt(p -> p.monthValue));
        Stack<String> resultStack = new Stack<>();
        for (Pair p : pairs) {
            resultStack.push(p.word);
            resultStack.push(p.month);
        }
        String[] output = new String[resultStack.size()];
        for (int i = resultStack.size() - 1; i >= 0; i--) {
            output[i] = resultStack.pop();
        }
        return output;
    }
    public static int getMonthValue(String month) {
        return switch (month.toLowerCase()) {
            case "january"   -> 1;
            case "february"  -> 2;
            case "march"     -> 3;
            case "april"     -> 4;
            case "may"       -> 5;
            case "june"      -> 6;
            case "july"      -> 7;
            case "august"    -> 8;
            case "september" -> 9;
            case "october"   -> 10;
            case "november"  -> 11;
            case "december"  -> 12;
            default          -> throw new IllegalArgumentException("Invalid month name: " + month);
        };
    }
    static class Pair {
        String word;
        String month;
        int monthValue;
        Pair(String word, String month) {
            this.word = word;
            this.month = month;
            this.monthValue = getMonthValue(month);
        }
    }
}