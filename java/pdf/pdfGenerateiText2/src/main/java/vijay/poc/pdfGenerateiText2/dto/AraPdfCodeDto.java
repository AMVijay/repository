/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vijay.poc.pdfGenerateiText2.dto;

import java.util.List;

/**
 * DTO class for PDF Export.
 * @author 135670
 */
public class AraPdfCodeDto {
    
    private String regulatoryCode;
    
    private String regulatoryLink;
    
    private String regulatoryDesc;
    
    private String regulatoryNotes;    
    
    private List<AraPdfSubsectionDto> subsectionList;

    public String getRegulatoryCode() {
        return regulatoryCode;
    }

    public void setRegulatoryCode(String regulatoryCode) {
        this.regulatoryCode = regulatoryCode;
    }

    public String getRegulatoryLink() {
        return regulatoryLink;
    }

    public void setRegulatoryLink(String regulatoryLink) {
        this.regulatoryLink = regulatoryLink;
    }

    public String getRegulatoryDesc() {
        return regulatoryDesc;
    }

    public void setRegulatoryDesc(String regulatoryDesc) {
        this.regulatoryDesc = regulatoryDesc;
    }

    public String getRegulatoryNotes() {
        return regulatoryNotes;
    }

    public void setRegulatoryNotes(String regulatoryNotes) {
        this.regulatoryNotes = regulatoryNotes;
    }

    public List<AraPdfSubsectionDto> getSubsectionList() {
        return subsectionList;
    }

    public void setSubsectionList(List<AraPdfSubsectionDto> subsectionList) {
        this.subsectionList = subsectionList;
    }
    
}
