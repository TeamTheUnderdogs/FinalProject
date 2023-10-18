package com.weare.testframework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class Utils {

    private static final Properties uiMappings = PropertiesManager.PropertiesManagerEnum.INSTANCE.getUiMappings();
    private static final Properties configProperties = PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties();
    public static final Logger LOGGER = LogManager.getRootLogger();

    public static WebDriver getWebDriver() {
        LOGGER.info("Initializing WebDriver");
        return CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver();
    }

    public static void tearDownWebDriver() {
        LOGGER.info("Quitting WebDriver");
        CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.quitDriver();
    }

    public static String getUIMappingByKey(String key) {
        String value = uiMappings.getProperty(key);
        return value != null ? value : key;
    }

    public static String getConfigPropertyByKey(String key) {
        String value = configProperties.getProperty(key);
        return value != null ? value : key;
    }

    public static LocalDate generateRandomDate(int startYear, int endYear) {
        Random random = new Random();

        int year = startYear + random.nextInt(endYear - startYear + 1);
        int month = 1 + random.nextInt(12);
        int day = 1 + random.nextInt(28);

        return LocalDate.of(year, month, day);
    }
}
