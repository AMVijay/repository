<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : template.xsl
    Created on : November 3, 2017, 10:27 AM
    Author     : Vijay
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.0">
    <xsl:output encoding="UTF-8" indent="yes" method="xml" standalone="no" omit-xml-declaration="no"/>

    <xsl:attribute-set name="myBorder">
      <xsl:attribute name="border">solid 0.1mm black</xsl:attribute>
    </xsl:attribute-set>
    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="araTemplate">
        <fo:root language="EN">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4-portrail" page-height="297mm" page-width="210mm" margin-top="5mm" margin-bottom="5mm" margin-left="3mm" margin-right="5mm">
                    <fo:region-body margin-top="5mm" margin-bottom="5mm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="A4-portrail">
                <fo:flow flow-name="xsl-region-body" border-collapse="collapse" reference-orientation="0">
                    <fo:block text-align="center">
                        <xsl:value-of select="title"/>
                    </fo:block>
                    <fo:table table-layout="fixed" 
                              width="100%" font-size="7pt" background-color="#F2F2F2"
                              margin-left="3mm" margin-top="5mm" margin-bottom="5mm" margin-right="10mm" 
                              border-collapse="separate" border-separation="3mm">                                           
                        <fo:table-body>
                            <fo:table-row space-before="50mm">
                                <fo:table-cell>
                                    <fo:block text-align="left">
                                        Accessories : 
                                        <xsl:value-of select="details/accessories"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block text-align="right">
                                        Created By :
                                        <xsl:value-of select="details/createdBy"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block text-align="left">
                                        Product Category :
                                        <xsl:value-of select="details/productCategory"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block text-align="right">
                                        Last Revision Date :
                                        <xsl:value-of select="details/lastRevisionDate"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block text-align="left">
                                        Status :
                                        <xsl:value-of select="details/status"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                    <xsl:for-each select="groupDetails/group">
                        <fo:block text-align="left" margin-left="3mm" font-size="8pt">
                            <xsl:value-of select="name"/>
                        </fo:block>
                        <xsl:for-each select="codeDetails/code">
                            <fo:table table-layout="fixed" width="100%" font-size="7pt" background-color="#F2F2F2"
                                      margin-left="3mm" margin-top="5mm" margin-bottom="5mm" margin-right="10mm"                                      
                                      border-collapse="separate" border-separation="3mm">                                                    
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block text-align="left">
                                                Regulatory Code :
                                                <xsl:value-of select="regulatoryCode"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block text-align="right">
                                                Regulatory Link :
                                                <xsl:value-of select="regulatoryLink"/>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block text-align="left">
                                                Regulatory Desc :
                                                <xsl:value-of select="regulatoryDesc"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block text-align="right">
                                                Regulatory Notes :
                                                <xsl:value-of select="regulatoryNotes"/>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>                            
                           
                            <fo:table table-layout="fixed" width="100%" font-size="7pt" 
                                      margin-left="3mm" margin-bottom="5mm" margin-right="10mm">
                                      <!-- border-collapse="separate" border-separation="2mm">   -->                                 
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell xsl:use-attribute-sets="myBorder" width="40mm">
                                            <fo:block text-align="left">Subsection Code</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell xsl:use-attribute-sets="myBorder" width="50mm">
                                            <fo:block text-align="left">Subsection Description</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell xsl:use-attribute-sets="myBorder">
                                            <fo:block text-align="left">Subsection Notes</fo:block>
                                        </fo:table-cell>                                       
                                    </fo:table-row>
                                    <xsl:for-each select="subsectionDetails/subsection">
                                        <fo:table-row>
                                            <fo:table-cell xsl:use-attribute-sets="myBorder">
                                                <fo:block text-align="left">                                                
                                                    <xsl:value-of select="code"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell xsl:use-attribute-sets="myBorder">
                                                <fo:block text-align="left">                                                
                                                    <xsl:value-of select="description"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell xsl:use-attribute-sets="myBorder">
                                                <fo:block text-align="left">                                                
                                                    <xsl:value-of select="notes"/>
                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </xsl:for-each>
                                </fo:table-body>
                            </fo:table>                            
                        </xsl:for-each>
                    </xsl:for-each>                  
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
