package com.ro.ClarkeWright.Manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Andrea Corriga
 */
public class FileManager {

    /**
     *
     * @param filename
     * @return
     */
    public static BufferedReader read(String filename) {

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return br;
    }

}


