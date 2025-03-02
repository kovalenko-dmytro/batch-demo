package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "template")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TemplateXmlLineModel {

    @XmlElement(name = "name")
    String name;

    @XmlElement(name = "description")
    String description;

    @XmlElement(name = "code")
    Integer code;

    @XmlElement(name = "enabled")
    Boolean enabled;
}
