package com.hse.dfa.backend.util.converters.attribute;

import com.hse.dfa.backend.model.user_info.RoleType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RoleTypeAttributeConverter implements AttributeConverter<RoleType, String> {
    @Override
    public String convertToDatabaseColumn(RoleType attribute) {
        return attribute.getRoleName();
    }

    @Override
    public RoleType convertToEntityAttribute(String dbData) {
        return dbData == null ? null : RoleType.fromRoleName(dbData);
    }
}
