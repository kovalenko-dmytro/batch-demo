package com.gmail.apach.dima.batch_demo.domain.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;
import java.util.UUID;

@Converter(autoApply = true)
public class UUIDAttributeConverter implements AttributeConverter<UUID, String> {

    @Override
    public String convertToDatabaseColumn(UUID uuid) {
        return Optional.ofNullable(uuid).map(UUID::toString).orElse(null);
    }

    @Override
    public UUID convertToEntityAttribute(String id) {
        return Optional.ofNullable(id).map(UUID::fromString).orElse(null);
    }
}
