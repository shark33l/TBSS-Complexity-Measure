package com.shakeel.complexity_measure.UI;
import com.shakeel.complexity_measure.controllers.MainController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainForm extends JFrame{

    private final JFileChooser openFileChooser;

    private JButton submitButton;
    private JPanel mainPanel;
    private JLabel fileLabel;
    private JLabel Header;
    private JComboBox fileCombo;

    MainController app = new MainController();

    public MainForm() {

        openFileChooser = new JFileChooser();
        openFileChooser.setCurrentDirectory(new File (System.getProperty("user.home")));


        add(mainPanel);
        setTitle("Complexity Tool");
        setSize(600, 400);

        //Combo Box Strings
        String[] comboSelectionStrings = {"File", "Folder"};

        fileCombo.addItem(comboSelectionStrings[0]);
        fileCombo.addItem(comboSelectionStrings[1]);

        fileCombo.setSelectedIndex(0);
        openFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        openFileChooser.setFileFilter(new FileNameExtensionFilter("Java Files", "java"));
        openFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("C++ Files", "cpp"));

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int returnValue = openFileChooser.showOpenDialog(getParent());

                if (returnValue == JFileChooser.APPROVE_OPTION){
                    try{
                        fileLabel.setText("Generating Report for " + openFileChooser.getSelectedFile().getName() + " - " + fileCombo.getSelectedItem() );
                        fileLabel.setText("" + openFileChooser.getCurrentDirectory() + " - " + fileCombo.getSelectedItem() );

                        try{
                            if (openFileChooser.getSelectedFile().isFile()){
                                app.fileRead("" + openFileChooser.getSelectedFile());
                            } else {
                                System.out.println("Directory Selected");
                            }
                        }catch(Exception ex){
                            JOptionPane.showMessageDialog(null,"Error - " + ex);
                        }
                    }catch(Exception IOex){
                        JOptionPane.showMessageDialog(null, "Error - " + IOex);
                    }
                } else {
                    fileLabel.setText("No File Choosen");
                }
            }
        });
    }

}
