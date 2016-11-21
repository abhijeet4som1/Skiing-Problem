package com.skiing.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.skiing.service.SkiingService;

/**
 * starting point of the program
 * 
 * @author Abhijeet
 *
 */
public class StartPoint {

    public static void main(String[] args) {

        String fileData = getFileData();
        if (null != fileData && fileData.trim().length() > 0) {
            Pattern pattern = Pattern.compile("(\\d+)");
            Matcher matcher = pattern.matcher(fileData);
            SkiingService sp = null;
            // first two digits are row and column
            matcher.find();
            matcher.find();
            sp = new SkiingService(Integer.parseInt(matcher.group()));
            while (matcher.find()) {
                sp.addNextMRange(Integer.parseInt(matcher.group()));
            }
            System.out.println("Result:" + sp.getResultStr());
        }
    }

    /**
     * getting file data
     * 
     * @return
     */
    public static String getFileData() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(StartPoint.class.getResourceAsStream("Map.txt")))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
            System.out.println("Exception while reading file.");
            e.printStackTrace();
        }
        return null;
    }
}
