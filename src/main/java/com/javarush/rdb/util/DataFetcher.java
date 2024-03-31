package com.javarush.rdb.util;

import com.javarush.rdb.dao.CityDAO;
import com.javarush.rdb.domain.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class DataFetcher {

    public List<City> fetchData(SessionFactory sessionFactory, CityDAO cityDAO) {
        try (Session session = sessionFactory.getCurrentSession()) {
            List<City> allCities = new ArrayList<>();
            session.beginTransaction();

            int totalCount = cityDAO.getTotalCount();
            int step = 500;
            for (int i = 0; i < totalCount; i += step) {
                allCities.addAll(cityDAO.getItems(i, step));
            }
            session.getTransaction().commit();
            return allCities;
        }
    }
}
