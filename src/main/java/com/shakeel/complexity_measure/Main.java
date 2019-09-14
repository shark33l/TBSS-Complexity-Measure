package com.shakeel.complexity_measure;


import com.shakeel.complexity_measure.UI.MainForm;
import com.shakeel.complexity_measure.controllers.MainController;

import javax.swing.*;

public class Main {


    public static void main(String[] args) throws Exception{

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainForm app = new MainForm();

                app.setVisible(true);
            }
        });

    }
}
