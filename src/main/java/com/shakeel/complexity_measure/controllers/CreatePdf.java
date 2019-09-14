package com.shakeel.complexity_measure.controllers;


import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.shakeel.complexity_measure.model.ComplexityModel;

import java.io.IOException;
import java.util.ArrayList;

public class CreatePdf {

    private int countCr;
    private String csToken;
    private int countCs;
    private int countCtc;
    private int countCnc;
    private int countCi;
    private int countTW;
    private int countCps;
    private int countCp;



    public void createPdfDocument(ArrayList<ComplexityModel> complexityModelArrayList, String saveLocation){

        String prevFilePath = "";
        int totalProgramCp = 0;
        int totalFiles = 0;

        try{
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(saveLocation));
            Document doc = new Document(pdfDoc, PageSize.A4);

            // Line Seperator Styled
            SolidLine line = new SolidLine(1f);
            LineSeparator ls = new LineSeparator(line);
            ls.setWidth(520);
            ls.setMarginTop(5);
            ls.setMarginBottom(15);

            // Header Styling
            Style headerStyle = new Style();
            headerStyle.setFontSize(16);
            headerStyle.setMarginBottom(10);
            headerStyle.setMarginTop(5);
            headerStyle.setBold();

            // Subtype Styling
            Style subtype = new Style();
            subtype.setFontSize(12);
            subtype.setMarginBottom(1);
            subtype.setMarginTop(1);

            // Cell Styling
            Style normal = new Style();
            normal.setFontSize(8);

            // Header Styling
            Style footerStyle = new Style();
            footerStyle.setFontSize(8);
            footerStyle.setBold();

            doc.add(new Paragraph("Complexity Measurement").addStyle(headerStyle).setTextAlignment(TextAlignment.CENTER));

            for (ComplexityModel complexityModelFileName : complexityModelArrayList) {

                if(complexityModelFileName.getFilePath() != prevFilePath) {

                    prevFilePath = complexityModelFileName.getFilePath();
                    int countTotalCp = 0;
                    totalFiles += 1;

                    doc.add(new Paragraph("Type : " + complexityModelFileName.getFileType()).addStyle(subtype));
                    doc.add(new Paragraph("File Name : " + complexityModelFileName.getFileName()).addStyle(subtype));
                    doc.add(new Paragraph("File Path : " + complexityModelFileName.getFilePath()).addStyle(subtype));
                    doc.add(ls);

                    Table table = new Table(new float[]{3, 29, 8, 8, 5, 3, 8, 3, 3, 3, 3, 3, 3, 4, 4}).useAllAvailableWidth();
                    table.setFixedLayout();

                    //Add Table Headers
                    table.addHeaderCell("#").addStyle(normal);
                    table.addHeaderCell("Line").addStyle(normal);
                    table.addHeaderCell("D. Method").addStyle(normal);
                    table.addHeaderCell("C. Method").addStyle(normal);
                    table.addHeaderCell("Rec").addStyle(normal);
                    table.addHeaderCell("T.M.C").addStyle(normal);
                    table.addHeaderCell("Cs Tokens").addStyle(normal);
                    table.addHeaderCell("Cs").addStyle(normal);
                    table.addHeaderCell("Ctc").addStyle(normal);
                    table.addHeaderCell("Cnc").addStyle(normal);
                    table.addHeaderCell("Ci").addStyle(normal);
                    table.addHeaderCell("TW").addStyle(normal);
                    table.addHeaderCell("Cps").addStyle(normal);
                    table.addHeaderCell("Cr").addStyle(normal);
                    table.addHeaderCell("CpsCr").addStyle(normal);

                    //Add Remaining Cells
                    for (ComplexityModel complexityModel : complexityModelArrayList) {
                        if (complexityModel.getFilePath() == prevFilePath) {

                            countTotalCp += complexityModel.getCountCp();

                            table.addCell(Integer.toString(complexityModel.getLineNo())).addStyle(normal);
                            table.addCell(complexityModel.getLine()).addStyle(normal);
                            table.addCell(complexityModel.getMethodName()).addStyle(normal);
                            table.addCell(complexityModel.getFunctionName()).addStyle(normal);
                            table.addCell(complexityModel.getRecursive().toString()).addStyle(normal);
                            table.addCell(Integer.toString(complexityModel.getTimesVisited())).addStyle(normal);
                            table.addCell(complexityModel.getCsToken()).addStyle(normal);
                            table.addCell(Integer.toString(complexityModel.getCountCs())).addStyle(normal);
                            table.addCell(Integer.toString(complexityModel.getCountCtc())).addStyle(normal);
                            table.addCell(Integer.toString(complexityModel.getCountCnc())).addStyle(normal);
                            table.addCell(Integer.toString(complexityModel.getCountCi())).addStyle(normal);
                            table.addCell(Integer.toString(complexityModel.getCountTW())).addStyle(normal);
                            table.addCell(Integer.toString(complexityModel.getCountCps())).addStyle(normal);
                            table.addCell(Integer.toString(complexityModel.getCountCr())).addStyle(normal);
                            table.addCell(Integer.toString(complexityModel.getCountCp())).addStyle(normal);
                        }
                    }

                    // Footer
                    Cell cell;
                    cell = new Cell(1, 7).add(new Paragraph("Total CP").addStyle(normal).setTextAlignment(TextAlignment.RIGHT));
                    table.addCell(cell);
                    cell = new Cell(1, 8).add(new Paragraph(Integer.toString(countTotalCp)).addStyle(normal).setTextAlignment(TextAlignment.RIGHT));
                    table.addCell(cell);

                    totalProgramCp += countTotalCp;

                    doc.add(table);
                    doc.add(new AreaBreak());
                }

            }

            //Create Summary
            doc.add(new Paragraph("Summary").addStyle(subtype));

            Table table = new Table(new float[]{3, 1}).useAllAvailableWidth();
            table.setFixedLayout();

            //Add Table Headers
            table.addHeaderCell("Description").addStyle(normal);
            table.addHeaderCell("Count/Quantity").addStyle(normal);

            table.addCell("Total Number of files Read").addStyle(normal);
            table.addCell(Integer.toString(totalFiles)).addStyle(normal);

            table.addCell("Total Program Cp").addStyle(normal);
            table.addCell(Integer.toString(totalProgramCp)).addStyle(normal);

            doc.add(table);

            // Acronyms
            doc.add(new Paragraph("Acronyms").addStyle(subtype).setMarginTop(20));
            Table table2 = new Table(new float[]{1, 3}).useAllAvailableWidth();
            table2.setFixedLayout();

            //Add Table Headers
            table2.addHeaderCell("Acronym").addStyle(normal);
            table2.addHeaderCell("Meaning").addStyle(normal);

            table2.addCell("D. Method").addStyle(normal);
            table2.addCell("Declared Method").addStyle(normal);

            table2.addCell("C. Method").addStyle(normal);
            table2.addCell("Calling Method").addStyle(normal);

            table2.addCell("Rec").addStyle(normal);
            table2.addCell("Is Recursive (True/False)").addStyle(normal);

            table2.addCell("T.M.C").addStyle(normal);
            table2.addCell("# Times Method was Called").addStyle(normal);

            table2.addCell("Cs").addStyle(normal);
            table2.addCell("Complexity of a program statement due to size").addStyle(normal);

            table2.addCell("Ctc").addStyle(normal);
            table2.addCell("Complexity of a program statement due to type of control structures ").addStyle(normal);

            table2.addCell("Cnc").addStyle(normal);
            table2.addCell("Complexity of a program statement due to nesting of control structures").addStyle(normal);

            table2.addCell("Ci").addStyle(normal);
            table2.addCell("Complexity of a program statement due to inheritance").addStyle(normal);

            table2.addCell("TW").addStyle(normal);
            table2.addCell("Total complexity of a program statement").addStyle(normal);

            table2.addCell("Cps").addStyle(normal);
            table2.addCell("Complexity of a program statement").addStyle(normal);

            table2.addCell("Cr").addStyle(normal);
            table2.addCell("Complexity introduced due to recursion").addStyle(normal);

            table2.addCell("CpsCr").addStyle(normal);
            table2.addCell("Cps + Cr (Taken Line by line for Reference)").addStyle(normal);

            table2.addCell("Cp").addStyle(normal);
            table2.addCell("Complexity of a program ").addStyle(normal);


            doc.add(table2);

            doc.add(new Paragraph("Created by WE_42").setMarginTop(200));
            doc.add(new Paragraph("F.Rishan | De Silva D.B.J | Alawathugoda R.M.S.A | Aman M.N.S").setFontSize(8).setMarginTop(1));

            doc.close();

        }catch(IOException IOex){
            IOex.printStackTrace();
        }

        }

}
