import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ReadFileToString
{

    private static String[] words;

    public static void main(String[] args)
    {
        String filePath = "c:/Users/Joe/FileToRead.txt";

        String fileString =  readLineByLineJava8( filePath ) ;

        String wordCount = wordCount(fileString);
        System.out.println(wordCount);

        words = splitString(fileString);

        System.out.println(averageWordLength(words));

        ArrayList<String> answers = numberOfWordsOfEachLength(words, findLongestWord(words));

        for (int i = 0; i < answers.size() ; i++){
            System.out.println(answers.get(i));
        }
    }

    public static ArrayList<String> numberOfWordsOfEachLength(String[] words, int maxLength){
        int frequency = 0;
        ArrayList<Integer> greatestLength = new ArrayList<>();
        ArrayList<String> answers = new ArrayList<>();
        for(int i = 1; i <= maxLength; i++) {
            //Only show values where there is at least one word of given length
            int frequencyOfWordLength = numberOfWordsOfLength(words, i);
            if(frequencyOfWordLength != 0){
                answers.add("Number of words of length " + i + " is " + frequencyOfWordLength);
                if(frequencyOfWordLength > frequency){
                    frequency = frequencyOfWordLength;
                    greatestLength.clear();
                    greatestLength.add(i);
                } else if (frequencyOfWordLength == frequency) {
                    greatestLength.add(i);
                }
            }
        }
        String lengths = greatestLength.get(0).toString();
        for(int i = 1; i < greatestLength.size(); i++){
            lengths = lengths + " & " + greatestLength.get(i);
        }
        answers.add("The most frequently occurring word length is " + frequency + ", for length of " + lengths);
        return answers;
    }

    public static int numberOfWordsOfLength(String[] words, int length){
        int count = 0;
        for(int i = 0; i < words.length; i++){
            if(words[i].length() == length){
                count++;
            }
        }
        return count;
    }

    public static int findLongestWord(String[] words) {
        String maxLengthWord = "";
        for(int i = 0; i < words.length; i++){
            if(words[i].length() >= maxLengthWord.length()){
                maxLengthWord = words[i];
            }
        }
        return maxLengthWord.length();
    }

    public static String wordCount(String stringToRead){

        String output = "";
        String trim = stringToRead.trim();
        if (trim.isEmpty()){
            output = "String is empty";
        } else {
            output = "Word count = " + trim.split("\\s+").length;
        }
        return output;
    }

    public static String[] splitString(String stringToSplit){
        //Removing full stops, as these from test example these are not included in actual values
        String cleanString = stringToSplit.replace(".", "");

        return cleanString.split("\\s+"); // split by whitespace
    }

    public static String averageWordLength(String[] words){
        int count = 0;
        int sum = 0;

        // iterate over each word and update the stats
        for (String word : words) {
            int wordLength = word.length();
            sum += wordLength;
            count++;
        }

        // calculate the average at the end
        BigDecimal average = new BigDecimal("0");
        if (count > 0) {
            average = new BigDecimal((double) sum / count);
        }


        BigDecimal scaledAverage = average.setScale(3, RoundingMode.UP);

        return "Average word length = " + scaledAverage;
    }




    //Read file content into the string with - Files.lines(Path path, Charset cs)

    private static String readLineByLineJava8(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }
}