package Soal2;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Arrays;

public class Soal2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter character's name (Josuke/Jotaro/Okuyasu/Koichi/Rohan):");
        String character = scanner.nextLine().trim();
        System.out.println("Enter suspect's name (Separate by spaces):");
        String namesLine = scanner.nextLine().trim();
        Queue<String> suspectsQueue = new LinkedList<>(Arrays.asList(namesLine.split("\\s+")));
        Stack<String> arrestedStack = new Stack<>();
        Stand investigator;
        switch (character.toLowerCase()) {
            case "josuke"  -> investigator = new JosukeStand();
            case "jotaro"  -> investigator = new JotaroStand();
            case "okuyasu" -> investigator = new OkuyasuStand();
            case "koichi"  -> investigator = new KoichiStand();
            case "rohan"   -> investigator = new RohanStand();
            default -> {
                scanner.close();
                throw new IllegalArgumentException("Invalid character name: " + character + ". Valid options are: Josuke, Jotaro, Okuyasu, Koichi, Rohan.");
            }
        }
        Stand detective = new DetectiveStand();
        while (!suspectsQueue.isEmpty()) {
            String suspect = suspectsQueue.poll();
            if (investigator.expose(suspect)) {
                detective.arrest(suspect, arrestedStack);
            }
        }
        if (arrestedStack.isEmpty()) {
            System.out.println("\n" + character + " exposed no one.");
            System.out.println("Arrested: []");
        } else {
            System.out.println("\n" + character + " exposed someone!");
            System.out.println("Arrested: " + arrestedStack.toString());
        }
        scanner.close();
    }
}
class Stand {
    public boolean expose(String name) {
        throw new UnsupportedOperationException("This Stand cannot expose suspects. Method must be overridden.");
    }
    public void arrest(String name, Stack<String> jail) {
        throw new UnsupportedOperationException("This Stand cannot arrest suspects. Method must be overridden.");
    }
}
class DetectiveStand extends Stand {
    @Override
    public boolean expose(String name) {
        return false;
    }
    @Override
    public void arrest(String name, Stack<String> jail) {
        jail.push(name);
    }
}
class JosukeStand extends Stand {
    @Override
    public boolean expose(String name) {
        return name.toLowerCase().startsWith("k");
    }
}
class JotaroStand extends Stand {
    @Override
    public boolean expose(String name) {
        return name.length() <= 3;
    }
}
class OkuyasuStand extends Stand {
    @Override
    public boolean expose(String name) {
        String lowerName = name.toLowerCase();
        for (int i = 0; i < lowerName.length() - 1; i++) {
            if (lowerName.charAt(i) == lowerName.charAt(i + 1)) {
                return true;
            }
        }
        return false;
    }
}
class KoichiStand extends Stand {
    @Override
    public boolean expose(String name) {
        int vowelsCount = 0;
        String lowerName = name.toLowerCase();
        for (char c : lowerName.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowelsCount++;
            }
        }
        return vowelsCount >= 3;
    }
}
class RohanStand extends Stand {
    @Override
    public boolean expose(String name) {
        if (name.isEmpty()) return false;
        String reversed = new StringBuilder(name).reverse().toString();
        return name.equalsIgnoreCase(reversed);
    }
}