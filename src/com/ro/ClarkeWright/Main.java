package com.ro.ClarkeWright;
import java.io.BufferedReader;
import java.io.IOException;

import com.ro.ClarkeWright.Manager.FileManager;
import com.ro.ClarkeWright.Core.Instance;

public class Main {

    public static void main(String[] args) throws IOException {
        //FileManager fileManager = new FileManager();
        BufferedReader file = FileManager.read("./data/A-VRP/A-n32-k5.vrp");
        Instance instance = new Instance(file);

    }
}
