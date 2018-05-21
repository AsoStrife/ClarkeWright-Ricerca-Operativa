package com.ro.clarkewright.manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

    /**
     * Read a file passed as parameter (with path)
     * @param path of the file to read
     * @return the buffer reader object
     */
    public static BufferedReader read(String path) {

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return br;
    }

}


