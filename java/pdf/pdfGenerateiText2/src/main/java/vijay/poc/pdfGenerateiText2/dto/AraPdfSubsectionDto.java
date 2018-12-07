/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vijay.poc.pdfGenerateiText2.dto;

/**
 * DTO class for PDF Export.
 * @author 135670
 */
public class AraPdfSubsectionDto {
 
    private String subsectionCode;
    
    private String subsectionDescription;
    
    private String subsectionNotes;

    public String getSubsectionCode() {
        return subsectionCode;
    }

    public void setSubsectionCode(String subsectionCode) {
        this.subsectionCode = subsectionCode;
    }

    public String getSubsectionDescription() {
        return subsectionDescription;
    }

    public void setSubsectionDescription(String subsectionDescription) {
        this.subsectionDescription = subsectionDescription;
    }

    public String getSubsectionNotes() {
        return subsectionNotes;
    }

    public void setSubsectionNotes(String subsectionNotes) {
        this.subsectionNotes = subsectionNotes;
    }

    
}
