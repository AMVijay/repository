package vijay.poc.pdfGenerateiText2.service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfDestination;
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import vijay.poc.pdfGenerateiText2.dto.AraPdfCodeDto;
import vijay.poc.pdfGenerateiText2.dto.AraPdfGroupDto;
import vijay.poc.pdfGenerateiText2.dto.AraPdfSubsectionDto;
import vijay.poc.pdfGenerateiText2.dto.AraPdfTemplateDto;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * PdfGenerateService is service class to generate PDF from AraPdfTemplateDto using iText.
 * @author Vijay (AM.Vijay@gmail.com)
 */
public class PdfGenerateService {
    
    /**
     * Method to generate PDF from AraPdfTemplateDto.
     * @param araPdfTemplate
     * @throws FileNotFoundException
     * @throws DocumentException 
     */
    public void createPdf(AraPdfTemplateDto araPdfTemplate) throws FileNotFoundException, DocumentException{
        
        // step 1: creation of a document-object
        Document document = new Document(PageSize.A4);
        // step 2: we create a writer that listens to the document
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("test.pdf"));
        // step 3: we open the document
        document.open();
        
        // Update Title
        updateTitle(document,araPdfTemplate);
        
        // Update TemplateDetails
        updateTemplateDetails(document,araPdfTemplate);
        
        // Update Group Details
        updateGroupDetails(document,writer,araPdfTemplate);        
        
        document.close();
    }

    /**
     * Method to update title in PDF Document.
     * @param document
     * @param araPdfTemplate
     * @throws DocumentException 
     */
    private void updateTitle(Document document, AraPdfTemplateDto araPdfTemplate) throws DocumentException {
        
        Paragraph paragraph = new Paragraph(araPdfTemplate.getTemplateTitle());
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
    }

    /**
     * Method to update Template Details in PDF Document.
     * @param document
     * @param araPdfTemplate
     * @throws DocumentException 
     */
    private void updateTemplateDetails(Document document, AraPdfTemplateDto araPdfTemplate) throws DocumentException {
        
        PdfPTable table = new PdfPTable(3);        
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.setSpacingBefore(5);
        table.setWidthPercentage(100);        
        
        Paragraph paragraph = new Paragraph("Accessory : " + araPdfTemplate.getAccessoryName());
        paragraph.getFont().setSize(9);
        PdfPCell row1Column1 = new PdfPCell(paragraph);
        row1Column1.setColspan(2);
        row1Column1.setBackgroundColor(Color.LIGHT_GRAY);
        row1Column1.setBorder(Rectangle.NO_BORDER);
        table.addCell(row1Column1);
        
        paragraph = new Paragraph("Created by : " + araPdfTemplate.getCreatedBy());
        paragraph.getFont().setSize(9);
        PdfPCell row1Column2 = new PdfPCell(paragraph);
        row1Column2.setBackgroundColor(Color.LIGHT_GRAY);
        row1Column2.setBorder(Rectangle.NO_BORDER);
        table.addCell(row1Column2);
        
        paragraph = new Paragraph("Product Category : " + araPdfTemplate.getProductCategory());
        paragraph.getFont().setSize(9);
        PdfPCell row2Column1 = new PdfPCell(paragraph);
        row2Column1.setColspan(2);
        row2Column1.setBackgroundColor(Color.LIGHT_GRAY);
        row2Column1.setBorder(Rectangle.NO_BORDER);
        table.addCell(row2Column1);
        
        paragraph = new Paragraph("Last Revision Date : " + araPdfTemplate.getLastRevisionDate());
        paragraph.getFont().setSize(9);
        PdfPCell row2Column2 = new PdfPCell(paragraph);
        row2Column2.setBackgroundColor(Color.LIGHT_GRAY);
        row2Column2.setBorder(Rectangle.NO_BORDER);
        table.addCell(row2Column2);
        
        paragraph = new Paragraph("Status : " + araPdfTemplate.getStatus());
        paragraph.getFont().setSize(9);
        PdfPCell row3Column1 = new PdfPCell(paragraph);
        row3Column1.setColspan(3);
        row3Column1.setBackgroundColor(Color.LIGHT_GRAY);
        row3Column1.setBorder(Rectangle.NO_BORDER);
        table.addCell(row3Column1);
                      
        document.add(table);
    }

    /**
     * Method to update Group Details in PDF Document.
     * @param document
     * @param writer
     * @param araPdfTemplate
     * @throws DocumentException 
     */
    private void updateGroupDetails(Document document, PdfWriter writer, AraPdfTemplateDto araPdfTemplate) throws DocumentException {
        
        for(AraPdfGroupDto araPdfGroup: araPdfTemplate.getAraPdfGroupList()){
            
            PdfDestination pdfDestination = new PdfDestination(PdfDestination.FITH, writer.getVerticalPosition(true));
            PdfOutline groupOutline = new PdfOutline(writer.getRootOutline(), pdfDestination, araPdfGroup.getGroupName(),true);
            
            Paragraph paragraph = new Paragraph(araPdfGroup.getGroupName());
            paragraph.getFont().setSize(10);
            document.add(paragraph);
            
            // Update Code Details
            updateCodeDetails(document,groupOutline,writer,araPdfGroup);
        }
        
    }

    /**
     * Method to update Code Details in PDF Document.
     * @param document
     * @param groupOutline
     * @param writer
     * @param araPdfGroup
     * @throws DocumentException 
     */
    private void updateCodeDetails(Document document, PdfOutline groupOutline, PdfWriter writer, AraPdfGroupDto araPdfGroup) throws DocumentException {
        
        
        for(AraPdfCodeDto araPdfCode : araPdfGroup.getCodeList()){
            
            PdfDestination pdfDestination = new PdfDestination(PdfDestination.FITH, writer.getVerticalPosition(true));
            PdfOutline codeOutline = new PdfOutline(groupOutline, pdfDestination, araPdfCode.getRegulatoryCode(),true);
            
            PdfPTable table = new PdfPTable(3);
            table.setSpacingBefore(5);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);            
            table.setWidthPercentage(100);            

            Paragraph paragraph = new Paragraph("Regulatory Code : " + araPdfCode.getRegulatoryCode());
            paragraph.getFont().setSize(9);
            PdfPCell row1Column1 = new PdfPCell(paragraph);
            row1Column1.setColspan(2);
            row1Column1.setBackgroundColor(Color.LIGHT_GRAY);
            row1Column1.setBorder(Rectangle.NO_BORDER);
            table.addCell(row1Column1);

            paragraph = new Paragraph("Regulatory Link : " + araPdfCode.getRegulatoryLink());
            paragraph.getFont().setSize(9);
            PdfPCell row1Column2 = new PdfPCell(paragraph);
            row1Column2.setBackgroundColor(Color.LIGHT_GRAY);
            row1Column2.setBorder(Rectangle.NO_BORDER);
            table.addCell(row1Column2);

            paragraph = new Paragraph("Regulatory Desc : " + araPdfCode.getRegulatoryDesc());
            paragraph.getFont().setSize(9);
            PdfPCell row2Column1 = new PdfPCell(paragraph);
            row2Column1.setBackgroundColor(Color.LIGHT_GRAY);
            row2Column1.setBorder(Rectangle.NO_BORDER);
            row2Column1.setColspan(2);
            table.addCell(row2Column1);

            paragraph = new Paragraph("Regulatory Notes : " + araPdfCode.getRegulatoryNotes());
            paragraph.getFont().setSize(9);
            PdfPCell row2Column2 = new PdfPCell(paragraph);
            row2Column2.setBackgroundColor(Color.LIGHT_GRAY);
            row2Column2.setBorder(Rectangle.NO_BORDER);
            table.addCell(row2Column2);
            
            document.add(table);
            
            // Update Subsection Details
            updateSubsectionDetails(document,araPdfCode);
            
        }
        
    }

    /**
     * Method to update Subsection Details in PDF Document.
     * @param document
     * @param araPdfCode
     * @throws DocumentException 
     */
    private void updateSubsectionDetails(Document document, AraPdfCodeDto araPdfCode) throws DocumentException {
        
        PdfPTable table = new PdfPTable(3);
        table.setSpacingBefore(3);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);        
        table.setWidthPercentage(100);
        
        Paragraph paragraph = new Paragraph("Subsection Code");
        paragraph.getFont().setSize(9);
        PdfPCell row1Column1 = new PdfPCell(paragraph);
        table.addCell(row1Column1);

        paragraph = new Paragraph("Subsection Desc");
        paragraph.getFont().setSize(9);
        PdfPCell row1Column2 = new PdfPCell(paragraph);
        table.addCell(row1Column2);

        paragraph = new Paragraph("Subsection Notes");
        paragraph.getFont().setSize(9);
        PdfPCell row1Column3 = new PdfPCell(paragraph);
        table.addCell(row1Column3);
        
        for(AraPdfSubsectionDto araPdfSubsection : araPdfCode.getSubsectionList()){
            
            paragraph = new Paragraph(araPdfSubsection.getSubsectionCode());
            paragraph.getFont().setSize(9);
            PdfPCell column1 = new PdfPCell(paragraph);
            table.addCell(column1);

            paragraph = new Paragraph(araPdfSubsection.getSubsectionDescription());
            paragraph.getFont().setSize(9);
            PdfPCell column2 = new PdfPCell(paragraph);
            table.addCell(column2);

            paragraph = new Paragraph(araPdfSubsection.getSubsectionNotes());
            paragraph.getFont().setSize(9);
            PdfPCell column3 = new PdfPCell(paragraph);
            table.addCell(column3);
        }
        
        document.add(table);
        
    }
}
