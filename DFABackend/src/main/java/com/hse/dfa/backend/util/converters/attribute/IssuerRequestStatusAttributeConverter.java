package com.hse.dfa.backend.util.converters.attribute;

import com.hse.dfa.backend.model.admin_requests.IssuerRequestStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static java.util.Optional.ofNullable;

// TODO Подумать что сделать с null

@Converter
public class IssuerRequestStatusAttributeConverter implements AttributeConverter<IssuerRequestStatus, String> {
    @Override
    public String convertToDatabaseColumn(IssuerRequestStatus attribute) {
        return ofNullable(attribute)
            .map(IssuerRequestStatus::name)
            .orElse(null);
    }

    @Override
    public IssuerRequestStatus convertToEntityAttribute(String dbData) {
        return ofNullable(dbData)
            .map(String::toUpperCase)
            .map(IssuerRequestStatus::valueOf)
            .orElse(null);
    }
}
