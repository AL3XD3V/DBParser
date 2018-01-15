package com.company.Logic;

import com.company.Forms.MainForm;

import java.io.*;
import java.util.ArrayList;

public class Helper {

    public static boolean isTxt(String fileName) {
        return fileName.contains(".txt");
    }

    public static String getFileName(String fileName) {
        String name = "";
        for (int i = fileName.length() - 1; i > 1; i--) {
            if (fileName.charAt(i) == '\\') {
                break;
            } else {
                name = fileName.charAt(i) + name;
            }
        }
        return name;
    }

    public static boolean checkTags(ArrayList<String> tags, String str) {
        for (String tag : tags) {
            if (str.contains(tag)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> parseTags(String tags) {
        ArrayList<String> tagsArray = new ArrayList<>();
        String currentTag = "";
        for (int i = 0; i < tags.length(); i++) {
            if (tags.charAt(i) != ' ') {
                currentTag += tags.charAt(i);
            } else {
                tagsArray.add(currentTag);
                currentTag = "";
                continue;
            }
        }
        tagsArray.add(currentTag);

        return tagsArray;
    }

    public static ArrayList<String> sortByTag(ArrayList<String> tags, ArrayList<String> str) {
        ArrayList<String> buff = new ArrayList<>();
        boolean found = false;
        for (String tag : tags) {
            for (String element : str) {
                if (element.contains(tag)) {
                    found = true;
                    buff.add(element);
                }
            }
            if (!found) {
                buff.add(MainForm.emptyTagField.getText());
            }
            found = false;
        }
        return buff;
    }

    public static String clearTag(ArrayList<String> tags, String str) {
        for (String tag : tags) {
            if (str.contains(tag)) {
                return str.substring(6);
            }
        }
        return str;
    }

    public static String clearSpecials(String str) {
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
        return newStr.trim();
    }

    public static File getSourceDir() {
        return new File(MainForm.sourceDirField.getText());
    }

    public static String getDestinationDir() {
        return MainForm.destinationDirField.getText();
    }

    public static String getFilePostfix() {
        return MainForm.filePostfixField.getText();
    }

    public static String[] getListOfFiles() {
        return getSourceDir().list();
    }

    public static ArrayList<String> files() {
        ArrayList<String> txtFiles = new ArrayList<>();
        String[] listOfFiles = getListOfFiles();
        for (String currentFile : listOfFiles) {
            if (Helper.isTxt(currentFile)) {
                txtFiles.add(currentFile);
            }
        }
        return txtFiles;
    }

    public static ArrayList<String> tags() {
        return Helper.parseTags(MainForm.tagsField.getText());
    }

    public static BufferedReader openReadStream(String fileName) throws FileNotFoundException {
        FileInputStream fStream = new FileInputStream(fileName);
        return new BufferedReader(new InputStreamReader(fStream));
    }

    public static BufferedWriter openWriteStream(String fileName) throws IOException {
        return new BufferedWriter(new FileWriter(fileName));
    }

    public static String newFileName(String fileName) {
        return getDestinationDir() + getFileName(fileName).substring(0, getFileName(fileName).length() - 4) + getFilePostfix() + ".txt";
    }

}