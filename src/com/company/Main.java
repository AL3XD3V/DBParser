package com.company;

import java.io.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {

    private static JLabel sourceDirLabel = new JLabel("Директория с исходными файлами:");
    private static JTextField sourceDirField = new JTextField(System.getProperty("user.dir"), 5);

    private static JLabel destinationDirLabel = new JLabel("Директория с целевыми файлами:");
    private static JTextField destinationDirField = new JTextField(System.getProperty("user.dir") + "\\parsed\\", 5);

    private static JLabel filePostfixLabel = new JLabel("Постфикс целевых файлов:");
    private static JTextField filePostfixField = new JTextField("_parsed", 5);

    private JCheckBox divideBox = new JCheckBox("Разделять файл каждые", false);
    private JTextField divideField = new JTextField("0", 5);
    private JLabel divideLabel = new JLabel("записей");

    private static JLabel tagsLabel = new JLabel("Искомые тэги:");
    private static JTextField tagsField = new JTextField("#500 #400", 5);

    private static JLabel emptyTaglabel = new JLabel("Если тэг не встретился:");
    private static JTextField emptyTagField = new JTextField("Неизвестен", 5);

    private static JLabel textWrapperLabel = new JLabel("Обертка элемента:");
    private static JTextField textWrapperField = new JTextField("\"", 5);

    private static JLabel elementDivisorLabel = new JLabel("Разделитель элементов:");
    private static JTextField elementDivisorField = new JTextField(",", 5);

    private static JLabel lineDivisorLabel = new JLabel("Разделитель строки:");
    private static JTextField lineDivisorField = new JTextField(";", 5);

    private static JButton processButton = new JButton("Парсинг");
    private static JButton helpButton = new JButton("Помощь");

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

        helpButton.addActionListener(new HelpButtonEventListener());
        processButton.addActionListener(new ProcessButtonEventListener());

        setContentPane(outerBox);
        setSize(500, 400);
    }

    class HelpButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String message = "";
            message += "Данный парсер обрабатывает все файлы .txt в указанном каталоге,\n";
            message += "и формирует новые файлы только с необходимыми полями.\n";
            message += "Поля:\n";
            message += "\"Директория с исходными файлами\" - папка с файлами, которые нужно обработать.\n";
            message += "\"Директория с целевыми файлами:\" - папка, где будут лежать распарсенные файлы.\n";
            message += "\"Разделять файл каждые\" - если файл большой, можно разделять его по записям.\n";
            message += "\"Постфикс целевых файлов:\" - добавляет в конец к имени распарсенных файлов.\n";
            message += "\"Искомые тэги\" - список выдираемых тэгов через пробел.\n";
            message += "\"Если тэг не встретился\" - что написать вместо не найденного тэга.\n";
            message += "\"Обертка элемента\" - во что оборачивать найденный элемент.\n";
            message += "\"Разделитель элементов\" - чем разделять элементы в строке.\n";
            message += "\"Разделитель строки\" - чем разделять записи (строки).\n";
            JOptionPane.showMessageDialog(null,
                    message,
                    "Help",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    class ProcessButtonEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            File projectDir = new File(Main.sourceDirField.getText());
            String message = "";
            message += projectDir + "\n";
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
                message += "No .txt files found!\n";
            } else {
                message += countOfTxtFiles + " files found!\n";
                for (String currentTxt : txtFiles) {
                    message += "Start parsing " + currentTxt + "...\n";
                    Boolean result = parse(projectDir + "\\" + currentTxt);
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

    private static boolean isTxt(String fileName) {
        return fileName.contains(".txt");
    }

    private static boolean parse(String fileName) {

        ArrayList<String> tags = parseTags(Main.tagsField.getText());

        String changedFileName = Main.destinationDirField.getText() + getFileName(fileName).substring(0, getFileName(fileName).length() - 4) + Main.filePostfixField.getText() + ".txt";
        try {
            FileInputStream fStream = new FileInputStream(fileName);
            BufferedReader inputFile = new BufferedReader(new InputStreamReader(fStream));
            BufferedWriter outputFile = new BufferedWriter(new FileWriter(changedFileName));
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
                        String local = Main.textWrapperField.getText() + clearSpecials(clearTag(tags, str)) + Main.textWrapperField.getText();
                        resultBuff += local + Main.elementDivisorField.getText();
                    }
                    outputFile.write(resultBuff.substring(0, resultBuff.length() - 1) + Main.lineDivisorField.getText());
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

    private static String getFileName(String fileName) {
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

    private static boolean checkTags(ArrayList<String> tags, String str) {
        for (String tag : tags) {
            if (str.contains(tag)) {
                return true;
            }
        }
        return false;
    }

    private static ArrayList<String> parseTags(String tags) {
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

    private static ArrayList<String> sortByTag(ArrayList<String> tags, ArrayList<String> str) {
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
                buff.add(Main.emptyTagField.getText());
            }
            found = false;
        }
        return buff;
    }

    private static String clearTag(ArrayList<String> tags, String str) {
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

    public static void main(String[] args) {

        Main app = new Main();
        app.setVisible(true);

    }

}