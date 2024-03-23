package com.javarush.rdb.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class CountryLanguageId implements Serializable {
    @Column(name = "CountryCode")
    private String code;

    private String language;
}
