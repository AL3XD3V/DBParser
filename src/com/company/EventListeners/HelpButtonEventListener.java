package com.company.EventListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpButtonEventListener implements ActionListener {
    @Override
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