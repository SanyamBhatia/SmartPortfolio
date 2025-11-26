package com.portfolio.util;

import java.io.*;
import java.util.*;

public class CSVReader {

    public static List<String[]> read(String filePath) {
        List<String[]> data = new ArrayList<>();

        try {
            File f = new File(filePath);
            if (!f.exists()) return data;

            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;

            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }

            br.close();
        } catch (Exception e) {}

        return data;
    }
}
