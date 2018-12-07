/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vijay.poc.pdfGenerateiText7.service;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfOutline;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.navigation.PdfDestination;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.TextAlignment;

import vijay.poc.pdfGenerateiText7.export.pdf.dto.AraPdfCodeDto;
import vijay.poc.pdfGenerateiText7.export.pdf.dto.AraPdfGroupDto;
import vijay.poc.pdfGenerateiText7.export.pdf.dto.AraPdfSubsectionDto;
import vijay.poc.pdfGenerateiText7.export.pdf.dto.AraPdfTemplateDto;

/**
 * Service to generate PDF using iText API.
 * @author 135670
 */
public class AraPdfGenerateService {
    /**
     * Method to generate New PDF And write it in iText PdfWriter object.
     * @param pdfWriter
     * @param araPdfTemplateDto
     * @return PdfDocument 
     */
    public PdfDocument createPdf(PdfWriter pdfWriter, AraPdfTemplateDto araPdfTemplateDto){
        
        PdfDocument pdfDocument = null;        
        
        if(araPdfTemplateDto != null){
            
            pdfDocument = new PdfDocument(pdfWriter);
            
            Document document = new Document(pdfDocument);
            
            // Update Group Details
            Paragraph title = new Paragraph(araPdfTemplateDto.getTemplateTitle());
            title.setTextAlignment(TextAlignment.CENTER);
            document.add(title);
            
            // Update Template Details
            updateTemplateDetails(document,araPdfTemplateDto);
            
            for(AraPdfGroupDto araPdfGroupDto : araPdfTemplateDto.getAraPdfGroupList()){
                updateGroupDetails(document,araPdfGroupDto);
                //document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            }
            
            pdfDocument.close();
        }
        
        return pdfDocument;
    }

    /**
     * Method to update ARA Group details in PDF Document.
     * @param document
     * @param araPdfGroupDto 
     */
    private void updateGroupDetails(Document document, AraPdfGroupDto araPdfGroupDto) {
        
        // Update Outline
        updateOutline(document,araPdfGroupDto);        
        
        // Update Group Details
        Paragraph paragraph = new Paragraph(araPdfGroupDto.getGroupName());
        paragraph.setDestination(araPdfGroupDto.getGroupName());
        
        Style style = new Style();
        style.setFontSize(11);
        
        paragraph.addStyle(style);
        document.add(paragraph);        
        
        // Update Category Details
        araPdfGroupDto.getCodeList().forEach((codeDto) -> {
            updateCode(document, codeDto);
            updateSubsection(document, codeDto);
        });
    }    

    /**
     * Method to update Code Details in PDF Document.
     * @param document
     * @param araPdfCodeDto 
     */
    private void updateCode(Document document, AraPdfCodeDto araPdfCodeDto) {
        // Update Category Details
//        Paragraph paragraph = new Paragraph(araPdfCodeDto.getRegulatoryCode());
//        paragraph.setDestination(araPdfCodeDto.getRegulatoryCode());
//        document.add(paragraph);
        
        Table codeDetailTable = new Table(2);
        
        Style smallFont = new Style();
        smallFont.setFontSize(8);
        
        Cell row1Column1 = new Cell();
        row1Column1.add("Regulatory Code :: " + araPdfCodeDto.getRegulatoryCode());
        row1Column1.addStyle(smallFont);        
        row1Column1.setBorder(Border.NO_BORDER);
        row1Column1.setBackgroundColor(Color.LIGHT_GRAY);
        codeDetailTable.addCell(row1Column1);        
        
//        Text regulatoryLink = new Text(araPdfCodeDto.getRegulatoryLink()).setFontColor(Color.BLUE);
        Cell row1Column2 = new Cell();        
        row1Column2.add("Regulatory Link :: " + araPdfCodeDto.getRegulatoryLink());        
        row1Column2.addStyle(smallFont);        
        row1Column2.setBorder(Border.NO_BORDER);
        row1Column2.setBackgroundColor(Color.LIGHT_GRAY);
        codeDetailTable.addCell(row1Column2);
        
        Cell row2Column1 = new Cell();
        row2Column1.add("Regulatory Desc :: " + araPdfCodeDto.getRegulatoryDesc());
        row2Column1.addStyle(smallFont);
        row2Column1.setBorder(Border.NO_BORDER);
        row2Column1.setBackgroundColor(Color.LIGHT_GRAY);
        codeDetailTable.addCell(row2Column1);
        
        Cell row2Column2 = new Cell();
        row2Column2.add("Regulatory Notes :: " + araPdfCodeDto.getRegulatoryNotes());
        row2Column2.addStyle(smallFont);
        row2Column2.setBorder(Border.NO_BORDER);
        row2Column2.setBackgroundColor(Color.LIGHT_GRAY);
        codeDetailTable.addCell(row2Column2);
        
        Cell row4 = new Cell(1,2);
        row4.setBorder(Border.NO_BORDER);
        //updateTableCell(row4column1, null, fontSize8Style, Border.NO_BORDER, Color.LIGHT_GRAY);        
        codeDetailTable.addCell(row4);
        
        document.add(codeDetailTable);
        
    }
    
    /**
     * Method to update Subsection Details in PDF Document.
     * @param document
     * @param codeDto 
     */
    private void updateSubsection(Document document, AraPdfCodeDto codeDto) {
        // Update SubCagetory Details
        
        Table table = new Table(3);
        
        Style fontSize8Style = new Style();
        fontSize8Style.setFontSize(8);
        fontSize8Style.setBold();
        
        Cell column1Header = new Cell();
        column1Header.add("Subsection Code");
        column1Header.addStyle(fontSize8Style);
        table.addCell(column1Header);        
        
        Cell column2Header = new Cell();
        column2Header.add("Subsection Desc");
        column2Header.addStyle(fontSize8Style);
        table.addCell(column2Header);
        
        Cell column3Header = new Cell();
        column3Header.add("Subsection Notes");
        column3Header.addStyle(fontSize8Style);        
        table.addCell(column3Header);
        
        for(AraPdfSubsectionDto subsection:codeDto.getSubsectionList()){
            Cell subsectionCodeCell = new Cell();
            subsectionCodeCell.add(subsection.getSubsectionCode());
            subsectionCodeCell.setFontSize(7);
            table.addCell(subsectionCodeCell);
            
            Cell subsectionDescCell = new Cell();
            subsectionDescCell.add(subsection.getSubsectionDescription());
            subsectionDescCell.setFontSize(7);
            table.addCell(subsectionDescCell);
            
            Cell subsectionNotesCell = new Cell();
            subsectionNotesCell.add(subsection.getSubsectionNotes());
            subsectionNotesCell.setFontSize(7);
            table.addCell(subsectionNotesCell);
            
        }
        
        Cell emptyRow = new Cell(1,3);
        emptyRow.setBorder(Border.NO_BORDER);
        table.addCell(emptyRow);
                
        document.add(table);
    }

    /**
     * Method to update ARA Template details in PDF Document.
     * @param document
     * @param araPdfTemplateDto 
     */
    private void updateTemplateDetails(Document document, AraPdfTemplateDto araPdfTemplateDto) {
        
        Table table = new Table(3);
        
        Style smallFont = new Style();
        smallFont.setFontSize(8);
        
        Cell row1column1 = new Cell(1,2);
        updateTableCell(row1column1,"Accessories : " + araPdfTemplateDto.getAccessoryName(),smallFont,Border.NO_BORDER,Color.LIGHT_GRAY);        
        table.addCell(row1column1);
        
        Cell row1column2 = new Cell(1,1);
        // Update Cell Content        
        updateTableCell(row1column2,"Created By : " + araPdfTemplateDto.getCreatedBy(),smallFont,Border.NO_BORDER,Color.LIGHT_GRAY);
        table.addCell(row1column2);
        
        Cell row2column1 = new Cell(1,2);
        updateTableCell(row2column1, "Product Category : " + araPdfTemplateDto.getProductCategory(), smallFont, Border.NO_BORDER, Color.LIGHT_GRAY);        
        table.addCell(row2column1);
        
        Cell row2column2 = new Cell(1,1);
        updateTableCell(row2column2, "Last Revision Date : " + araPdfTemplateDto.getLastRevisionDate(), smallFont, Border.NO_BORDER, Color.LIGHT_GRAY);        
        table.addCell(row2column2);
        
        Cell row3column1 = new Cell(1,3);
        updateTableCell(row3column1, "Status : " + araPdfTemplateDto.getStatus(), smallFont, Border.NO_BORDER, Color.LIGHT_GRAY);        
        table.addCell(row3column1);
        
        Cell row4column1 = new Cell(1,3);
        row4column1.setBorder(Border.NO_BORDER);
        //updateTableCell(row4column1, null, fontSize8Style, Border.NO_BORDER, Color.LIGHT_GRAY);        
        table.addCell(row4column1);
        
        document.add(table);
    }
    
    /**
     * Method to update Outline for each Group.
     * @param document
     * @param araPdfGroupDto 
     */
    private void updateOutline(Document document, AraPdfGroupDto araPdfGroupDto) {
        
        PdfOutline pdfOutline = document.getPdfDocument().getOutlines(false);
        pdfOutline = pdfOutline.addOutline(araPdfGroupDto.getGroupName());
        pdfOutline.addDestination(PdfDestination.makeDestination(new PdfString(araPdfGroupDto.getGroupName())));
        
    }

    /**
     * Method to update Table Cell Properties based on style input.
     * @param cell
     * @param content
     * @param font
     * @param border
     * @param color 
     */
    private void updateTableCell(Cell cell, String content, Style font, Border border, Color color) {
        
        if(content != null){
            cell.add(content);
        }
        cell.addStyle(font);        
        cell.setBorder(border);
        cell.setBackgroundColor(color);        
    }
    
}
