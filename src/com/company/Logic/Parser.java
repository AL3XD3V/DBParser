package com.company.Logic;

import com.company.Forms.MainForm;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    public static boolean parse() {
        ArrayList<String> files = Helper.files();
        Boolean flag = true;
        if (!MainForm.divideBox.isSelected()) {
            for (String file : files) {
                if (!parseOnes(Helper.getSourceDir() + "\\" + file)) {
                    flag = false;
                }
            }
        } else {
            for (String file : files) {
                if (!parseMany(Helper.getSourceDir() + "\\" + file)) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    private static boolean parseOnes(String fileName) {
        try {
            BufferedReader inputFile = Helper.openReadStream(fileName);
            BufferedWriter outputFile = Helper.openWriteStream(Helper.newFileName(fileName));
            String strLine;
            ArrayList<String> buff = new ArrayList<>();
            while ((strLine = inputFile.readLine()) != null){

                if (Helper.checkTags(Helper.tags(), strLine)) {
                    buff.add(strLine);
                }
                if (strLine.contains("***") && !buff.isEmpty()) {
                    buff = Helper.sortByTag(Helper.tags(), buff);
                    String resultBuff = "";
                    for (String str : buff) {
                        String local = MainForm.textWrapperField.getText() + Helper.clearSpecials(Helper.clearTag(Helper.tags(), str)) + MainForm.textWrapperField.getText();
                        resultBuff += local + MainForm.elementDivisorField.getText();
                    }
                    outputFile.write(resultBuff.substring(0, resultBuff.length() - 1) + MainForm.lineDivisorField.getText());
                    outputFile.flush();
                    buff.clear();
                }
            }
            inputFile.close();
            outputFile.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error reading file...\n");
        }
        return false;
    }

    private static boolean parseMany(String fileName) {
        try {
            BufferedReader inputFile = Helper.openReadStream(fileName);
            String strLine;
            ArrayList<String> buff = new ArrayList<>();
            int countOfLines = 0;
            int countOfFiles = 1;
            String toWrite = "";
            while ((strLine = inputFile.readLine()) != null){

                System.out.print(countOfFiles + "  " + countOfLines + "\n");

                if (Helper.checkTags(Helper.tags(), strLine)) {
                    buff.add(strLine);
                    countOfLines++;
                }
                if (countOfLines ==  Integer.parseInt(MainForm.divideField.getText())) {
                    countOfLines = 0;
                    File file = new File(Helper.newFilePartName(fileName, Integer.toString(countOfFiles)));
                    writeToFile(file, toWrite);
                    countOfFiles++;
                    toWrite = "";
                }
                if (strLine.contains("***") && !buff.isEmpty()) {
                    buff = Helper.sortByTag(Helper.tags(), buff);
                    String resultBuff = "";
                    for (String str : buff) {
                        String local = MainForm.textWrapperField.getText() + Helper.clearSpecials(Helper.clearTag(Helper.tags(), str)) + MainForm.textWrapperField.getText();
                        resultBuff += local + MainForm.elementDivisorField.getText();
                    }
                    resultBuff = resultBuff.substring(0, resultBuff.length() - 1) + MainForm.lineDivisorField.getText();
                    toWrite += resultBuff;
                    buff.clear();
                }
            }
            inputFile.close();
            if (!toWrite.isEmpty()) {
                File file = new File(Helper.newFilePartName(fileName, Integer.toString(countOfFiles)));
                writeToFile(file, toWrite);
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error reading file...\n");
        }
        return false;
    }

    public static boolean writeToFile(File file, String str) throws IOException {

        FileWriter wr = new FileWriter(file);
        wr.append(str);
        wr.close();
        return true;
    }

}