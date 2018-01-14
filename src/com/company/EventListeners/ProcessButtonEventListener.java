package com.company.EventListeners;

import com.company.Forms.MainForm;
import com.company.Logic.Helper;
import com.company.Logic.Parser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ProcessButtonEventListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        File projectDir = new File(MainForm.sourceDirField.getText());
        String message = "";
        message += projectDir + "\n";
        String[] listOfFiles = projectDir.list();
        int countOfTxtFiles = 0;
        ArrayList<String> txtFiles = new ArrayList<>();
        for (String currentFile : listOfFiles) {
            if (Helper.isTxt(currentFile)) {
                countOfTxtFiles++;
                txtFiles.add(currentFile);
            }
        }
        if (countOfTxtFiles == 0) {
            message += "No .txt files found!\n";
        } else {
            message += countOfTxtFiles + " files found!\n";
            for (String currentTxt : txtFiles) {
                message += "Start parsing " + currentTxt + "...\n";
                Boolean result = Parser.parse(projectDir + "\\" + currentTxt);
                if (result) {
                    message += currentTxt + " parsed successfully!\n";
                } else {
                    message += "Parsing of " + currentTxt + " failed.\n";
                }
            }
        }
        message += "Work done!";
        JOptionPane.showMessageDialog(null,
                message,
                "Parse",
                JOptionPane.PLAIN_MESSAGE);
    }
}