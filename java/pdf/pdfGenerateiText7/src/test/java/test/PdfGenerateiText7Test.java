package test;


import com.itextpdf.kernel.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

import vijay.poc.pdfGenerateiText7.export.pdf.dto.AraPdfCodeDto;
import vijay.poc.pdfGenerateiText7.export.pdf.dto.AraPdfGroupDto;
import vijay.poc.pdfGenerateiText7.export.pdf.dto.AraPdfSubsectionDto;
import vijay.poc.pdfGenerateiText7.export.pdf.dto.AraPdfTemplateDto;
import vijay.poc.pdfGenerateiText7.service.AraPdfGenerateService;

/**
 * JUnit Test class to validate PDFGeneration.
 * @author Vijay (AM.Vijay@gmail.com)
 */
public class PdfGenerateiText7Test {
   
    /**
     * Junit Test Method to validate PDFDocument generation.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    @Test
    public void generatePdfTest() throws FileNotFoundException, IOException{
        
        AraPdfTemplateDto araPdfTemplateDto = new AraPdfTemplateDto();
        araPdfTemplateDto.setTemplateTitle("Test Template");
        araPdfTemplateDto.setAccessoryName("All Weather Floor Mats,Carpet Floor Mats");
        araPdfTemplateDto.setProductCategory("Audio");
        araPdfTemplateDto.setStatus("Draft");
        araPdfTemplateDto.setCreatedBy("design engineer");
        araPdfTemplateDto.setLastRevisionDate("10/30/2017");

        araPdfTemplateDto.setAraPdfGroupList(new ArrayList<>());
        for(int i=0;i<5;i++){
            AraPdfGroupDto araPdfGroupDto = new AraPdfGroupDto();
            araPdfGroupDto.setGroupName("Group " + i);
            
            araPdfGroupDto.setCodeList(new ArrayList<>());
            
            for(int j=0;j<20;j++){
                AraPdfCodeDto araPdfCodeDto = new AraPdfCodeDto();
                araPdfCodeDto.setRegulatoryCode("40CFR571.21" + j);
                araPdfCodeDto.setRegulatoryDesc("Sample Description for regulatory Code");
                araPdfCodeDto.setRegulatoryLink("http://www.google.com");
                araPdfCodeDto.setRegulatoryNotes("Text Notes");
                araPdfCodeDto.setSubsectionList(new ArrayList<>());
                
                for(int k=0;k<10;k++){
                    AraPdfSubsectionDto subsectionDto = new AraPdfSubsectionDto();
                    subsectionDto.setSubsectionCode("subsection Code " + k);
                    subsectionDto.setSubsectionDescription("Subsection Description");
                    subsectionDto.setSubsectionNotes("Subsection Notes");
                    araPdfCodeDto.getSubsectionList().add(subsectionDto);
                }
                araPdfGroupDto.getCodeList().add(araPdfCodeDto);
            }
            
            araPdfTemplateDto.getAraPdfGroupList().add(araPdfGroupDto);
        }
        
        PdfWriter pdfWriter = new PdfWriter("test.pdf");
        
        AraPdfGenerateService service = new AraPdfGenerateService();
        service.createPdf(pdfWriter, araPdfTemplateDto);
        pdfWriter.close();
        Assert.assertTrue(true);
        
    }
}
