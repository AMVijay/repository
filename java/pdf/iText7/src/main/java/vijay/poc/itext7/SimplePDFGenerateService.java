package vijay.poc.itext7;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

/**
 * Class to demonstrate simple PDF Generation using itext7.
 * @author Vijay(AM.Vijay@gmail.com)
 */
public class SimplePDFGenerateService {
    
    private static final String FILENAME = "sample.pdf";
    
    /**
     * Main Method to create sample PDF.
     * @param args
     */
    public static void main(String[] args) {
        
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = new PdfWriter(FILENAME);
            
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            
            Document document = new Document(pdfDocument);
            
            document.add(new Paragraph("Hello, World"));
            
            document.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SimplePDFGenerateService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pdfWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(SimplePDFGenerateService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    
}
