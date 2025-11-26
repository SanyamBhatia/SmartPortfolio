package com.portfolio.service;

import com.portfolio.model.User;
import com.portfolio.util.CSVReader;
import com.portfolio.util.CSVWriter;

import java.io.*;
import java.util.*;

public class UserService {

    private final String USERS_FILE = "data/users.csv";

    public User login(String u, String p) {
        List<String[]> rows = CSVReader.read(USERS_FILE);
        for (String[] r : rows) {
            if (r[0].equals(u) && r[1].equals(p)) {
                return new User(u, p);
            }
        }
        return null;
    }

    public boolean register(String u, String p) {
        List<String[]> rows = CSVReader.read(USERS_FILE);

        for (String[] r : rows) {
            if (r[0].equals(u)) return false;
        }

        CSVWriter.append(USERS_FILE, new String[]{u, p});
        return true;
    }
}
