package com.company.Logic;

import com.company.Forms.MainForm;

import java.io.*;
import java.util.ArrayList;

public class Parser {

    public static boolean parse(String fileName) {

        ArrayList<String> tags = Helper.parseTags(MainForm.tagsField.getText());

        String changedFileName = MainForm.destinationDirField.getText() + Helper.getFileName(fileName).substring(0, Helper.getFileName(fileName).length() - 4) + MainForm.filePostfixField.getText() + ".txt";
        try {
            FileInputStream fStream = new FileInputStream(fileName);
            BufferedReader inputFile = new BufferedReader(new InputStreamReader(fStream));
            BufferedWriter outputFile = new BufferedWriter(new FileWriter(changedFileName));
            String strLine;
            ArrayList<String> buff = new ArrayList<>();
            while ((strLine = inputFile.readLine()) != null){

                if (Helper.checkTags(tags, strLine)) {
                    buff.add(strLine);
                }
                if (strLine.contains("***") && !buff.isEmpty()) {
                    buff = Helper.sortByTag(tags, buff);
                    String resultBuff = "";
                    for (String str : buff) {
                        String local = MainForm.textWrapperField.getText() + Helper.clearSpecials(Helper.clearTag(tags, str)) + MainForm.textWrapperField.getText();
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

}
