package com.portfolio.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    // Reads CSV file and returns list of String[]
    public static List<String[]> readCSV(String path) {
        List<String[]> rows = new ArrayList<>();

        try {
            File file = new File(path);
            if (!file.exists()) return rows;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                rows.add(line.split(","));
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Error reading file: " + path);
        }

        return rows;
    }

    // Append a new CSV row
    public static void appendCSV(String path, String[] data) {
        try {
            FileWriter fw = new FileWriter(path, true);
            fw.write(String.join(",", data) + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Error appending to file: " + path);
        }
    }

    // Overwrite file with new rows
    public static void writeCSV(String path, List<String[]> rows) {
        try {
            FileWriter fw = new FileWriter(path);

            for (String[] row : rows) {
                fw.write(String.join(",", row) + "\n");
            }

            fw.close();
        } catch (Exception e) {
            System.out.println("Error writing file: " + path);
        }
    }

    // Ensures a directory exists
    public static void ensureDirectory(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    // Ensures file exists (creates empty file if missing)
    public static void ensureFile(String filePath) {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("Error ensuring file: " + filePath);
        }
    }
}
