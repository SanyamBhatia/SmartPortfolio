package com.portfolio.util;

import java.io.*;
import java.util.*;

public class CSVWriter {

    public static void append(String filePath, String[] row) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(String.join(",", row) + "\n");
            fw.close();
        } catch (Exception e) {}
    }

    public static void writeAll(String filePath, List<String[]> rows) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (String[] r : rows) fw.write(String.join(",", r) + "\n");
            fw.close();
        } catch (Exception e) {}
    }
}
