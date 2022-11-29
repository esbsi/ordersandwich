package be.abis.ordersandwich.mapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BooleanConverter implements AttributeConverter<Boolean,String> {


    @Override
    public String convertToDatabaseColumn(Boolean aBoolean) {
        if(aBoolean==true){
            return "T";
        }
        return  "F";
    }

    @Override
    public Boolean convertToEntityAttribute(String s) {
        if (s.equalsIgnoreCase("F")){
            return false;
        }

        return true;
    }
}
