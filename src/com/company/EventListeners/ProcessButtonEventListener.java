package com.company.EventListeners;

import com.company.Logic.Helper;
import com.company.Logic.Parser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProcessButtonEventListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        String message = "";
        if (!Helper.files().isEmpty()) {
            if (Parser.parse()) {
                message += "Parsed successfully!";
            } else {
                message += "Parsing failed!";
            }
        } else {
            message += "No files found!";
        }

        JOptionPane.showMessageDialog(null,
                message,
                "Parse",
                JOptionPane.PLAIN_MESSAGE);
    }
}