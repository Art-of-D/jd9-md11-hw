package hibernate;


import com.google.gson.Gson;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter
public class ConverterGsLong implements AttributeConverter<List<Long>, Long> {

    @Override
    public Long convertToDatabaseColumn(List<Long> attribute) {
        return Long.valueOf(new Gson().toJson(attribute));
    }

    @Override
    public List<Long> convertToEntityAttribute(Long dbData) {
        return new Gson().fromJson(String.valueOf(dbData), List.class);
    }
}
