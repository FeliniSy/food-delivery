package org.fooddeliverymodified.reader;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class DistinctWordCalculator {

    public void countDiscardedWords(File file) throws IOException {
        String readBook = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

        String[] words = readBook.split("\\W+");
        int countBefore = words.length;

        Set<String> distinctWords = new HashSet<>();

        for (String word : words) {
            distinctWords.add(word.toLowerCase());
        }

        int countAfter = distinctWords.size();
        System.out.println("before " + countBefore + " after " + countAfter);

        writeToFile(countAfter, distinctWords);
    }

    public void writeToFile(int countOfWords, Set<String> distinctWords) throws IOException {

        try {
            File resultFile = new File("C:\\Users\\ninik\\Desktop\\Java projects\\food-delivery-modified\\src\\main\\resources\\Results.txt");

            if (!resultFile.exists()) {
                System.out.println("New File created" + resultFile.getName());
            } else {
                System.out.println("Already exists" + resultFile.getName());
            }

            FileWriter fw = new FileWriter(resultFile);
            fw.write(countOfWords + "\n" + "\n");
            for (String word : distinctWords) {
                fw.write(word + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/book.txt");
        DistinctWordCalculator calculator = new DistinctWordCalculator();
        calculator.countDiscardedWords(file);
    }
}

