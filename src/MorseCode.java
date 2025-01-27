import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MorseCode {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MorseLogic morseLogic = new MorseLogic();

        System.out.println("Välkommen till MorseCode-programmet!");
        System.out.println("1. Morse till Engelska");
        System.out.println("2. Engelska till Morse");
        System.out.print("Välj ett alternativ (1 eller 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Du ska rensa raden efter val

        if (choice == 1) {
            System.out.print("Ange morsekod (separera bokstäver med mellanslag): ");
            String morseInput = scanner.nextLine();
            try {
                String result = morseLogic.morseToEnglish(morseInput);
                System.out.println("Översatt till engelska: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("Fel: " + e.getMessage());
            }
        } else if (choice == 2) {
            System.out.print("Ange text att översätta till morse: ");
            String englishInput = scanner.nextLine().toUpperCase();
            try {
                String result = morseLogic.englishToMorse(englishInput);
                System.out.println("Översatt till morse: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("Fel: " + e.getMessage());
            }
        } else {
            System.out.println("Ogiltigt val. Programmet avslutas.");
        }

        scanner.close();
    }

    // Den inre klassen som hanterar logiken
    static class MorseLogic {
        private final Map<String, String> morseToEnglishMap = new HashMap<>();
        private final Map<String, String> englishToMorseMap = new HashMap<>();

        public MorseLogic() {
            initMaps();
        }

        private void initMaps() {
            String[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
            String[] morseCodes = {
                    ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
                    ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--",
                    "-..-", "-.--", "--.."
            };

            for (int i = 0; i < letters.length; i++) {
                morseToEnglishMap.put(morseCodes[i], letters[i]);
                englishToMorseMap.put(letters[i], morseCodes[i]);
            }
        }

        public String morseToEnglish(String morseCode) {
            if (morseCode == null || morseCode.isBlank()) {
                throw new IllegalArgumentException("Morsekoden får inte vara tom.");
            }

            StringBuilder result = new StringBuilder();
            String[] morseLetters = morseCode.trim().split(" ");

            for (String morseLetter : morseLetters) {
                String letter = morseToEnglishMap.get(morseLetter);
                if (letter == null) {
                    throw new IllegalArgumentException("Ogiltig morsekod: " + morseLetter);
                }
                result.append(letter);
            }

            return result.toString();
        }

        public String englishToMorse(String englishText) {
            if (englishText == null || englishText.isBlank()) {
                throw new IllegalArgumentException("Texten får inte vara tom.");
            }

            StringBuilder result = new StringBuilder();
            for (char ch : englishText.toCharArray()) {
                if (ch == ' ') continue; // Hoppa över mellanslag
                String morseCode = englishToMorseMap.get(String.valueOf(ch));
                if (morseCode == null) {
                    throw new IllegalArgumentException("Ogiltigt tecken: " + ch);
                }
                result.append(morseCode).append(" ");
            }

            return result.toString().trim();
        }
    }
}

