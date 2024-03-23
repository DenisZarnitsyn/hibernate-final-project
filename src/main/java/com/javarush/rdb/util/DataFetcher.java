package com.javarush.rdb.util;

import com.javarush.rdb.Main;
import com.javarush.rdb.domain.City;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DataFetcher {

    public List<City> fetchData(Main main) {
        try (Session session = main.sessionFactory.getCurrentSession()) {
            List<City> allCities = new ArrayList<>();
            session.beginTransaction();

            int totalCount = main.cityDAO.getTotalCount();
            int step = 500;
            for (int i = 0; i < totalCount; i += step) {
                allCities.addAll(main.cityDAO.getItems(i, step));
            }
            session.getTransaction().commit();
            return allCities;
        }
    }
}
