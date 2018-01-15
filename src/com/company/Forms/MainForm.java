package com.company.Forms;

import com.company.EventListeners.HelpButtonEventListener;
import com.company.EventListeners.ProcessButtonEventListener;

import javax.swing.*;

public class MainForm extends JFrame {

    private static JLabel sourceDirLabel = new JLabel("Директория с исходными файлами:");
    public static JTextField sourceDirField = new JTextField(System.getProperty("user.dir"), 5);

    private static JLabel destinationDirLabel = new JLabel("Директория с целевыми файлами:");
    public static JTextField destinationDirField = new JTextField(System.getProperty("user.dir") + "\\parsed\\", 5);

    private static JLabel filePostfixLabel = new JLabel("Постфикс целевых файлов:");
    public static JTextField filePostfixField = new JTextField("_parsed", 5);

    public static JCheckBox divideBox = new JCheckBox("Разделять файл каждые", false);
    public static JTextField divideField = new JTextField("0", 5);
    private static JLabel divideLabel = new JLabel("записей");

    private static JLabel tagsLabel = new JLabel("Искомые тэги:");
    public static JTextField tagsField = new JTextField("#700 #200", 5);

    private static JLabel emptyTagLabel = new JLabel("Если тэг не встретился:");
    public static JTextField emptyTagField = new JTextField("Неизвестен", 5);

    private static JLabel textWrapperLabel = new JLabel("Обертка элемента:");
    public static JTextField textWrapperField = new JTextField("\"", 5);

    private static JLabel elementDivisorLabel = new JLabel("Разделитель элементов:");
    public static JTextField elementDivisorField = new JTextField(",", 5);

    private static JLabel lineDivisorLabel = new JLabel("Разделитель строки:");
    public static JTextField lineDivisorField = new JTextField(";", 5);

    private static JButton processButton = new JButton("Парсинг");
    private static JButton helpButton = new JButton("Помощь");

    public MainForm() {
        super("Irbis64 DBParser");
        this.setBounds(400,400, 500, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        box11.add(emptyTagLabel);
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

}
