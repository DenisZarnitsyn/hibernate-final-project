package com.javarush.rdb.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Name", length = 35, nullable = false, columnDefinition = "CHAR(35) DEFAULT ''")
    private String name;

    @Column(name = "CountryCode", length = 3, nullable = false, columnDefinition = "CHAR(3) DEFAULT ''")
    private String countryCode;

    @Column(name = "District", length = 20, nullable = false, columnDefinition = "CHAR(20) DEFAULT ''")
    private String district;

    @Column(name = "Population", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer population;
}
