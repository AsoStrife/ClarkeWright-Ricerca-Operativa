package com.ro.ClarkeWright.Core;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Instance {
    private BufferedReader file;

    private String name;
    private String comment;
    private String type;
    private int dimension;
    private String edgeWeightType;
    private int capacity;

    private ArrayList<Node> nodes;
    private int[] demands;
    private int[] depotSection;
    private String eof;

    public Instance(BufferedReader file) throws IOException {
        this.file = file;


        //String string = file.readLine();
        String string = file.lines().collect(Collectors.joining("\n"));
        //System.out.println(string);

        String regex = "([A-Z]*) : (.*)";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            System.out.println("Full match: " + matcher.group(0));
            for (int i = 1; i <= matcher.groupCount(); i++) {
                System.out.println("Group " + i + ": " + matcher.group(i));
            }
        }


    }

    // Regular expression for string fields
    // ([A-Z]*) : (.*)

    // Regular expression for NODE_COORD_SECTION
    // ^([0-9]*) ([0-9]*) ([0-9]*) ?([0-9]*)$

    // Regular expression for DEMAND_SECTION
    // ^([0-9]*) ([0-9]*) ?([0-9]*)$
}
