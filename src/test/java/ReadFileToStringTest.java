import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;


public class ReadFileToStringTest {


    @Test
    public void averageWordLengthTest(){
        String[] words = ReadFileToString.splitString("This sentence has 5 words.");
        String average = ReadFileToString.averageWordLength(words);
        assertEquals("Average word length = 4.201", average);
    }

    @Test
    public void numberOfWordsOfEachLengthTest(){
        String[] words = ReadFileToString.splitString("This sentence has 5 words.");
        ArrayList<String> result = ReadFileToString.numberOfWordsOfEachLength(words, 8);
        assertEquals("Number of words of length 1 is 1", result.get(0));
        assertEquals("Number of words of length 3 is 1", result.get(1));
        assertEquals("Number of words of length 4 is 1", result.get(2));
        assertEquals("Number of words of length 5 is 1", result.get(3));
        assertEquals("Number of words of length 8 is 1", result.get(4));
        assertEquals("The most frequently occurring word length is 1, for length of 1 & 3 & 4 & 5 & 8", result.get(5));
    }

    @Test
    public void numberOfWordsOfLengthTest(){
        String[] words = ReadFileToString.splitString("This sentence has 5 words.");
        int result = ReadFileToString.numberOfWordsOfLength(words, 8);
        assertEquals(1, result);
    }

    @Test
    public void findLongestWordTest(){
        String[] words = ReadFileToString.splitString("This sentence has 5 words.");
        int result = ReadFileToString.findLongestWord(words);
        assertEquals(8, result);
    }

    @Test
    public void splitStringTest() {
        String[] result = ReadFileToString.splitString("This sentence has 5 words.");
        assertEquals("This", result[0]);
        assertEquals("sentence", result[1]);
        assertEquals("has", result[2]);
        assertEquals("5", result[3]);
        assertEquals("words", result[4]);
    }

    @Test
    public void wordCountTestEmptyString() {
        String result = ReadFileToString.wordCount("");
        assertEquals("String is empty", result);
    }

    @Test
    public void wordCountTest() {
        String result = ReadFileToString.wordCount("This sentence has 5 words.");
        assertEquals("Word count = 5", result);
    }
}
