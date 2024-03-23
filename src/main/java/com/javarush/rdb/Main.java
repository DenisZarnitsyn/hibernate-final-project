package com.javarush.rdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.rdb.dao.CityDAO;
import com.javarush.rdb.dao.CountryDAO;
import com.javarush.rdb.domain.City;
import com.javarush.rdb.domain.Country;
import com.javarush.rdb.domain.CountryLanguage;
import com.javarush.rdb.util.DataFetcher;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Properties;

import static java.util.Objects.nonNull;

public class Main {
    private final SessionFactory sessionFactory;
    private final ObjectMapper mapper;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final JedisPool jedisPool;

    public Main() {
        sessionFactory = prepareRelationalDb();
        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
        mapper = new ObjectMapper();
        jedisPool = prepareRedis();
    }

    public static void main(String[] args) {
        Main main = new Main();
        List<City> allCities = main.fetchData(main);
        for (City city : allCities) {
            System.out.println(city);
        }
        main.shutdown();
    }

    private List<City> fetchData(Main main) {
        return new DataFetcher().fetchData(main);
    }

    private void shutdown() {
        if (nonNull(sessionFactory)) {
            sessionFactory.close();
        }
        if (nonNull(jedisPool)) {
            jedisPool.close();
        }
    }

    private SessionFactory prepareRelationalDb() {
        protected final SessionFactory sessionFactory;
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        properties.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/world");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "root");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "validate");
        properties.put(Environment.STATEMENT_BATCH_SIZE, "100");

        sessionFactory = new Configuration()
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(CountryLanguage.class)
                .addProperties(properties)
                .buildSessionFactory();
        return sessionFactory;
    }

    private JedisPool prepareRedis() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10);
        poolConfig.setMaxIdle(5);
        poolConfig.setMinIdle(1);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);

        return new JedisPool(poolConfig, "localhost", 6379);
    }
}
