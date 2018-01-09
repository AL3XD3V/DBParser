package com.company;

import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {

    private JLabel sourceDirLabel = new JLabel("Директория с исходными файлами:");
    private JTextField sourceDirField = new JTextField(System.getProperty("user.dir"), 5);

    private JLabel destinationDirLabel = new JLabel("Директория с целевыми файлами:");
    private JTextField destinationDirField = new JTextField(System.getProperty("user.dir") + "\\parsed\\", 5);

    private JLabel filePostfixLabel = new JLabel("Постфикс целевых файлов:");
    private JTextField filePostfixField = new JTextField("_parsed", 5);

    private JCheckBox divideBox = new JCheckBox("Разделять файл каждые", false);
    private JTextField divideField = new JTextField("0", 5);
    private JLabel divideLabel = new JLabel("записей");

    private JLabel tagsLabel = new JLabel("Искомые тэги:");
    private JTextField tagsField = new JTextField("#500, #400", 5);

    private JLabel emptyTaglabel = new JLabel("Если тэг не встретился:");
    private JTextField emptyTagField = new JTextField("Неизвестен", 5);

    private JLabel textWrapperLabel = new JLabel("Обертка элемента:");
    private JTextField textWrapperField = new JTextField("\"", 5);

    private JLabel elementDivisorLabel = new JLabel("Разделитель элементов:");
    private JTextField elementDivisorField = new JTextField(",", 5);

    private JLabel lineDivisorLabel = new JLabel("Разделитель строки:");
    private JTextField lineDivisorField = new JTextField(";", 5);

    private JButton processButton = new JButton("Парсинг");
    private JButton helpButton = new JButton("Помощь");

    public Main() {
        super("Irbis64 DBParser");
        this.setBounds(400,400, 500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int separator = 10;

        Box outerBox = Box.createVerticalBox();

        Box box1 = Box.createHorizontalBox();
        box1.add(sourceDirLabel);
        box1.add(Box.createHorizontalStrut(separator));
        box1.add(sourceDirField);

        Box box2 = Box.createHorizontalBox();
        box2.add(destinationDirLabel);
        box2.add(Box.createHorizontalStrut(separator));
        box2.add(destinationDirField);

        Box box3 = Box.createHorizontalBox();
        box3.add(filePostfixLabel);
        box3.add(Box.createHorizontalStrut(separator));
        box3.add(filePostfixField);

        Box box4 = Box.createHorizontalBox();
        box4.add(divideBox);
        box4.add(Box.createHorizontalStrut(separator));
        box4.add(divideField);
        box4.add(Box.createHorizontalStrut(separator));
        box4.add(divideLabel);

        Box box5 = Box.createHorizontalBox();
        box5.add(filePostfixLabel);
        box5.add(Box.createHorizontalStrut(separator));
        box5.add(filePostfixField);

        Box box6 = Box.createHorizontalBox();
        box6.add(tagsLabel);
        box6.add(Box.createHorizontalStrut(separator));
        box6.add(tagsField);

        Box box11 = Box.createHorizontalBox();
        box11.add(emptyTaglabel);
        box11.add(Box.createHorizontalStrut(separator));
        box11.add(emptyTagField);

        Box box7 = Box.createHorizontalBox();
        box7.add(textWrapperLabel);
        box7.add(Box.createHorizontalStrut(separator));
        box7.add(textWrapperField);

        Box box8 = Box.createHorizontalBox();
        box8.add(elementDivisorLabel);
        box8.add(Box.createHorizontalStrut(separator));
        box8.add(elementDivisorField);

        Box box9 = Box.createHorizontalBox();
        box9.add(lineDivisorLabel);
        box9.add(Box.createHorizontalStrut(separator));
        box9.add(lineDivisorField);

        Box box10 = Box.createHorizontalBox();
        box10.add(processButton);
        box10.add(Box.createHorizontalStrut(separator));
        box10.add(helpButton);

        outerBox.add(box1);
        outerBox.add(Box.createVerticalStrut(separator));
        outerBox.add(box2);
        outerBox.add(Box.createVerticalStrut(separator));
        outerBox.add(box3);
        outerBox.add(Box.createVerticalStrut(separator));
        outerBox.add(box4);
        outerBox.add(Box.createVerticalStrut(separator));
        outerBox.add(box5);
        outerBox.add(Box.createVerticalStrut(separator));
        outerBox.add(box6);
        outerBox.add(Box.createVerticalStrut(separator));
        outerBox.add(box11);
        outerBox.add(Box.createVerticalStrut(separator));
        outerBox.add(box7);
        outerBox.add(Box.createVerticalStrut(separator));
        outerBox.add(box8);
        outerBox.add(Box.createVerticalStrut(separator));
        outerBox.add(box9);
        outerBox.add(Box.createVerticalStrut(separator));
        outerBox.add(box10);
        outerBox.add(Box.createVerticalStrut(separator));

        helpButton.addActionListener(new ButtonEventListener());

        setContentPane(outerBox);
        setSize(500, 400);
    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String message = "";
            message += "This is Help!\n";
            JOptionPane.showMessageDialog(null,
                    message,
                    "Help",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }


    public static void main(String[] args) {

        Main app = new Main();
        app.setVisible(true);

        File projectDir = new File(System.getProperty("user.dir"));
        System.out.print(projectDir + "\n");
        String[] listOfFiles = projectDir.list();
        int countOfTxtFiles = 0;
        ArrayList<String> txtFiles = new ArrayList<>();
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

        String[] tags = {"#700:", "#200:"};

        String changedfileName = fileName.substring(0, fileName.length() - 4) + "_parsed.txt";
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader inputFile = new BufferedReader(new InputStreamReader(fstream));
            BufferedWriter outputFile = new BufferedWriter(new FileWriter(changedfileName));
            String strLine;
            ArrayList<String> buff = new ArrayList<>();
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
        return newStr.trim();
    }
}