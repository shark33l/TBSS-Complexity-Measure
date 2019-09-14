package com.shakeel.complexity_measure.UI;
import com.shakeel.complexity_measure.controllers.MainController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainForm extends JFrame{

    private final JFileChooser openFileChooser;
    private final JFileChooser saveFileChooser;


    private JButton submitButton;
    private JPanel mainPanel;
    private JLabel fileLabel;
    private JLabel headerTitle;
    private JButton sourceLocationButton;
    private JButton saveDestinationButton;
    private JLabel sourceLabel;
    private JLabel saveLabel;

    //File Variables
    private String openFileType;
    private Path openFilePath;
    private String openFileName;

    private String saveFileName;
    private String saveFilePath;

    MainController app = new MainController();

    public MainForm() {

        openFileChooser = new JFileChooser();
        saveFileChooser = new JFileChooser();

//        openFileChooser.setCurrentDirectory(new File (System.getProperty("user.home")));


        add(mainPanel);
        setTitle("Complexity Tool");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Combo Box Strings
        String[] comboSelectionStrings = {"File", "Folder"};

        //Open File Dialog Settings
        openFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        openFileChooser.setAcceptAllFileFilterUsed(false);
        openFileChooser.setFileFilter(new FileNameExtensionFilter("Java Files", "java"));
        openFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("C++ Files", "cpp"));

        //Save File Dialog Settings
        saveFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        saveFileChooser.setAcceptAllFileFilterUsed(false);
        saveFileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));

        submitButton.setEnabled(false);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    if (openFileChooser.getSelectedFile().isFile()){
                        app.generateReport(openFileType, openFilePath, openFileName, saveFilePath);
                    } else {
//                                app.directoryRead(openFileChooser.getSelectedFile().toPath(), openFileChooser.getSelectedFile().getName());
                        app.generateReport(openFileType, openFilePath, openFileName, saveFilePath);

                        System.out.println("Directory Selected");
                    }

                    fileLabel.setText("Generated Report for " + openFileName + " - " + openFileType);

                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,"Error - " + ex);
                }
            }
        });
        sourceLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int returnValue = openFileChooser.showOpenDialog(getParent());

                if (returnValue == JFileChooser.APPROVE_OPTION){
                    try{
                        if (openFileChooser.getSelectedFile().isFile()){

                            openFileType = "File";
                            openFileName = openFileChooser.getSelectedFile().getName();
                            openFilePath = Paths.get(openFileChooser.getSelectedFile().toString());

                        } else {

                            openFileType = "Directory";
                            openFileName = openFileChooser.getSelectedFile().getName();
                            openFilePath = openFileChooser.getSelectedFile().toPath();

                        }
                        submitButton.setEnabled(true);
                        sourceLabel.setText(openFilePath.toString());
                    }catch(Exception IOex){
                        JOptionPane.showMessageDialog(null, "Error - " + IOex);
                    }
                } else {
                    sourceLabel.setText("No File Choosen");
                }



            }
        });
        saveDestinationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int returnValue = saveFileChooser.showSaveDialog(getParent());

                if (returnValue == JFileChooser.APPROVE_OPTION){
                    try{
                        saveFilePath = saveFileChooser.getSelectedFile().toString() + ".pdf";
                        saveLabel.setText(saveFilePath);
                    }catch(Exception IOex){
                        JOptionPane.showMessageDialog(null, "Error - " + IOex);
                    }
                } else {
                    saveFilePath = (new File (System.getProperty("user.home"))).toString() + "/ComplexityResult.pdf";
                    saveLabel.setText("Default Location - " + (new File (System.getProperty("user.home"))).toString() + "/ComplexityResult.pdf");
                }


            }
        });
    }

}
