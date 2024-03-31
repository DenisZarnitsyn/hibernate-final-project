package com.javarush.rdb.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "countrylanguage")
public class CountryLanguage {
    @EmbeddedId
    private CountryLanguageId id;

    @ManyToOne
    @MapsId("countryCode")
    @JoinColumn(name = "CountryCode", referencedColumnName = "Code")
    private Country country;

    @Column(name = "Language", length = 30, nullable = false, columnDefinition = "CHAR(30) DEFAULT ''")
    private String language;

    @Column(name = "IsOfficial", nullable = false, columnDefinition = "ENUM('T','F') DEFAULT 'F'")
    private Character isOfficial;

    @Column(name = "Percentage", nullable = false, columnDefinition = "DECIMAL(4,1) DEFAULT 0.0")
    private BigDecimal percentage;
}
