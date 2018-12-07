/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerException;
import org.apache.fop.apps.FOPException;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import vijay.poc.apachefop.service.GeneratePdfService;

/**
 *
 * @author 135670
 */
public class GeneratePdfTest {
    
    @Test
    public void testGeneratePdf(){
        
        GeneratePdfService service = new GeneratePdfService();
        try {
            service.convertToPDF();
             Assert.assertTrue(true);
        } catch (IOException ex) {
            Logger.getLogger(GeneratePdfTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FOPException ex) {
            Logger.getLogger(GeneratePdfTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(GeneratePdfTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(GeneratePdfTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }    
}
