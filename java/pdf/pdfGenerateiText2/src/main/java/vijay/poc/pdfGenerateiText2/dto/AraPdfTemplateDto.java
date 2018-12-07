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
public class AraPdfTemplateDto {
    
    private String templateTitle;
    
    private String accessoryName;
    
    private String productCategory;
    
    private String status;
    
    private String createdBy;
    
    private String lastRevisionDate;
    
    private List<AraPdfGroupDto> araPdfGroupList;

    public String getTemplateTitle() {
        return templateTitle;
    }

    public void setTemplateTitle(String templateTitle) {
        this.templateTitle = templateTitle;
    }

    public String getAccessoryName() {
        return accessoryName;
    }

    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastRevisionDate() {
        return lastRevisionDate;
    }

    public void setLastRevisionDate(String lastRevisionDate) {
        this.lastRevisionDate = lastRevisionDate;
    }
    
    public List<AraPdfGroupDto> getAraPdfGroupList() {
        return araPdfGroupList;
    }

    public void setAraPdfGroupList(List<AraPdfGroupDto> araPdfGroupList) {
        this.araPdfGroupList = araPdfGroupList;
    }
  
}
