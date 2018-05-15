package com.ro.ClarkeWright.Manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

    public static BufferedReader read(String filename) {

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            /*
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
            }
            */
        } catch (IOException e) {
            e.printStackTrace();
        }

        return br;
    }

}


