package com.javarush.rdb.transform;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.rdb.domain.City;
import com.javarush.rdb.domain.Country;
import com.javarush.rdb.domain.CountryLanguage;

public class DataTransformer {

    private final ObjectMapper objectMapper;

    public DataTransformer() {
        this.objectMapper = new ObjectMapper();
    }

    public String transformCityToJSON(City city) throws JsonProcessingException {
        return objectMapper.writeValueAsString(city);
    }

    public String transformCountryToJSON(Country country) throws JsonProcessingException {
        return objectMapper.writeValueAsString(country);
    }

    public String transformCountryLanguageToJSON(CountryLanguage countryLanguage) throws JsonProcessingException {
        return objectMapper.writeValueAsString(countryLanguage);
    }
}

