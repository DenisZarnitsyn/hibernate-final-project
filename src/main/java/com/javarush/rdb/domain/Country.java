package com.javarush.rdb.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "Code", length = 3, nullable = false, columnDefinition = "CHAR(3) DEFAULT ''")
    private String code;

    @Column(name = "Name", length = 52, nullable = false, columnDefinition = "CHAR(52) DEFAULT ''")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "Continent", nullable = false, columnDefinition = "ENUM('Asia','Europe','North America','Africa','Oceania','Antarctica','South America') DEFAULT 'Asia'")
    private Continent continent;

    @Column(name = "Region", length = 26, nullable = false, columnDefinition = "CHAR(26) DEFAULT ''")
    private String region;

    @Column(name = "SurfaceArea", nullable = false, columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private BigDecimal surfaceArea;

    @Column(name = "IndepYear")
    private Short indepYear;

    @Column(name = "Population", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer population;

    @Column(name = "LifeExpectancy", columnDefinition = "DECIMAL(3,1) DEFAULT NULL")
    private BigDecimal lifeExpectancy;

    @Column(name = "GNP", columnDefinition = "DECIMAL(10,2) DEFAULT NULL")
    private BigDecimal GNP;

    @Column(name = "GNPOld", columnDefinition = "DECIMAL(10,2) DEFAULT NULL")
    private BigDecimal GNPOld;

    @Column(name = "LocalName", length = 45, nullable = false, columnDefinition = "CHAR(45) DEFAULT ''")
    private String localName;

    @Column(name = "GovernmentForm", length = 45, nullable = false, columnDefinition = "CHAR(45) DEFAULT ''")
    private String governmentForm;

    @Column(name = "HeadOfState", length = 60, columnDefinition = "CHAR(60) DEFAULT NULL")
    private String headOfState;

    @OneToOne(mappedBy = "country")
    private City capital;
}
