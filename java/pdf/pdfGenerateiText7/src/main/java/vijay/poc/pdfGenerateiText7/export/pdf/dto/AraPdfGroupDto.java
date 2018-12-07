/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vijay.poc.pdfGenerateiText7.export.pdf.dto;

import java.util.List;

/**
 * DTO class for PDF Export.
 * @author 135670
 */
public class AraPdfGroupDto {
    
    private String groupName;
    
    private List<AraPdfCodeDto> codeList;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<AraPdfCodeDto> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<AraPdfCodeDto> codeList) {
        this.codeList = codeList;
    }    
}
