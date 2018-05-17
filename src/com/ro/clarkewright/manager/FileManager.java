package com.ro.clarkewright.manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Andrea Corriga
 */
public class FileManager {

    /**
     * Read a file passing as parameter (with path)
     * @param path
     * @return
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


