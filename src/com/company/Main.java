package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("user.dir"));
        String[] listOfFiles = projectDir.list();
        int countOfTxtFiles = 0;
        ArrayList<String> txtFiles = new ArrayList<String>();
        for (String currentFile : listOfFiles) {
            if (isTxt(currentFile)) {
                countOfTxtFiles++;
                txtFiles.add(currentFile);
            }
        }
        if (countOfTxtFiles == 0) {
            System.out.print("No .txt files found!\n");
        } else {
            System.out.print(countOfTxtFiles + " files found!\n");
            for (String currentTxt : txtFiles) {
                System.out.print("Start parsing " + currentTxt + "...\n");
                Boolean result = parse(projectDir + "\\" + currentTxt);
                if (result) {
                    System.out.print(currentTxt + " parsed successfully!\n");
                } else {
                    System.out.print("Parsing of " + currentTxt + " failed.\n");
                }
            }
        }
        System.out.print("Work done!");
    }

    private static boolean isTxt(String fileName) {
        return fileName.contains(".txt");
    }

    private static boolean parse(String fileName) {
        //System.out.print(fileName + "\n");

        String[] tags = {"#700:", "#200:"};

        String changedfileName = fileName.substring(0, fileName.length() - 4) + "_parsed.txt";
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader inputFile = new BufferedReader(new InputStreamReader(fstream));
            BufferedWriter outputFile = new BufferedWriter(new FileWriter(changedfileName));
            String strLine;
            ArrayList<String> buff = new ArrayList<String>();
            while ((strLine = inputFile.readLine()) != null){

                if (checkTags(tags, strLine)) {
                    buff.add(strLine);
                }
                if (strLine.contains("***") && !buff.isEmpty()) {
                    buff = sortByTag(tags, buff);
                    String resultBuff = "";
                    for (String str : buff) {
                        String local = "\"" + clearSpecials(clearTag(tags, str)) + "\"";
                        resultBuff += local + ",";
                    }
                    outputFile.write(resultBuff.substring(0, resultBuff.length() - 1) + ";\r\n");
                    outputFile.flush();
                    buff.clear();
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error reading file...\n");
        }
        return false;
    }

    private static boolean checkTags(String[] tags, String str) {
        for (String tag : tags) {
            if (str.contains(tag)) {
                return true;
            }
        }
        return false;
    }

    private static ArrayList<String> sortByTag(String[] tags, ArrayList<String> str) {
        ArrayList<String> buff = new ArrayList<String>();
        boolean found = false;
        for (String tag : tags) {
            for (String element : str) {
                if (element.contains(tag)) {
                    found = true;
                    buff.add(element);
                }
            }
            if (!found) {
                buff.add("Неизвестен");
            }
            found = false;
        }
        return buff;
    }

    private static String clearTag(String[] tags, String str) {
        for (String tag : tags) {
            if (str.contains(tag)) {
                return str.substring(6);
            }
        }
        return str;
    }

    private static String clearSpecials(String str) {
        String newStr = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '^') {
                newStr += ' ';
                i++;
                continue;
            } else if (str.charAt(i) == '"') {
                continue;
            } else {
                newStr += str.charAt(i);
            }
        }
        //newStr += str.charAt(str.length() - 1);
        //System.out.print(newStr + "\n");
        return newStr.trim();
    }
}