package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.mapper;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.*;
import jakarta.validation.Valid;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.validation.annotation.Validated;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Validated
public interface XmlMapper {

    TemplateExcelLine toTemplateExcelLine(@Valid TemplateXmlLine templateXmlLine);
    SettingExcelLine toSettingExcelLine(@Valid SettingXmlLine settingXmlLine);
    ConfigExcelLine toConfigExcelLine(@Valid ConfigXmlLine configXmlLine);
}
