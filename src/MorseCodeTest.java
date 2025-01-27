import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class MorseCodeTest {

    MorseCode.MorseLogic morseLogic = new MorseCode.MorseLogic();


    // Test 1: Testa Morse till Engelska med ett enkelt ord
    @Test
    void testMorseToEnglishSimple() {
        String morseInput = ".... . .-.. .-.. ---"; // Morse för "HELLO"
        String expected = "HELLO";
        String actual = morseLogic.morseToEnglish(morseInput);
        assertEquals(expected, actual, "Morse till Engelska misslyckades.");
    }

    // Test 2: Testa Engelska till Morse med ett enkelt ord
    @Test
    void testEnglishToMorseSimple() {
        String englishInput = "HELLO";
        String expected = ".... . .-.. .-.. ---"; // Morse för "HELLO"
        String actual = morseLogic.englishToMorse(englishInput);
        assertEquals(expected, actual, "Engelska till Morse misslyckades.");
    }

    // Test 3: Testa Morse till Engelska med tom inmatning
    @Test
    void testMorseToEnglishEmptyInput() {
        String emptyMorseInput = "";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> morseLogic.morseToEnglish(emptyMorseInput));
        assertEquals("Morsekoden får inte vara tom.", exception.getMessage(), "Felhantering för tom morsekod misslyckades.");
    }

    // Test 4: Testa Engelska till Morse med ogiltiga tecken
    @Test
    void testEnglishToMorseInvalidCharacter() {
        String invalidEnglishInput = "HELLO!";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> morseLogic.englishToMorse(invalidEnglishInput));
        assertEquals("Ogiltigt tecken: !", exception.getMessage(), "Felhantering för ogiltigt tecken misslyckades.");
    }

    // Test 5: Testa Morse till Engelska med en lång sträng
    @Test
    void testMorseToEnglishLongString() {
        String morseInput = ".... . .-.. .-.. --- .-- --- .-. .-.. -.."; // Morse för "HELLOWORLD"
        String expected = "HELLOWORLD";
        String actual = morseLogic.morseToEnglish(morseInput);
        assertEquals(expected, actual, "Lång morsekod till Engelska misslyckades.");
    }
}

