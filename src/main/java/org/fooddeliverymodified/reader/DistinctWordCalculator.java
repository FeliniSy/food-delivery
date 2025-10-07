package org.fooddeliverymodified.reader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DistinctWordCalculator {

    public static void main(String[] args) throws IOException {
        File inputFile = new File("src/main/resources/book.txt");
        File outputFile = new File("src/main/resources/Results.txt");

        String content = FileUtils.readFileToString(inputFile, StandardCharsets.UTF_8);

        String[] words = StringUtils.split(content, null);
        Set<String> uniqueWords = new HashSet<>();
        Arrays.stream(words)
                .map(String::toLowerCase)
                .forEach(uniqueWords::add);

        StringBuilder result = new StringBuilder();
        result.append("Total words: ").append(words.length).append("\n");
        result.append("Unique words: ").append(uniqueWords.size()).append("\n\n");
        uniqueWords.forEach(word -> result.append(word).append("\n"));

        FileUtils.writeStringToFile(outputFile, result.toString(), StandardCharsets.UTF_8);

        System.out.println("Processing done. Results saved to " + outputFile.getAbsolutePath());
    }
}

