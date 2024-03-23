package com.javarush.rdb.dao;

import com.javarush.rdb.domain.CountryLanguage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CountryLanguageDAO {
    private final SessionFactory sessionFactory;

    public CountryLanguageDAO() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public List<CountryLanguage> getAllCountryLanguages() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM CountryLanguage", CountryLanguage.class).getResultList();
        }
    }
}