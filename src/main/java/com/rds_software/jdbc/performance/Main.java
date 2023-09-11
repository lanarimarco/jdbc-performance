package com.rds_software.jdbc.performance;

import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Properties;

public class Main {

    private static final Properties config;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Main.class);

    static {
        try {
            final Path configPath = Paths.get(System.getProperty("user.dir"), "datasource.properties");
            LOGGER.info( "Loading: {}", configPath);
            config = new Properties();
            if (!Files.exists(configPath)) {
                config.setProperty("driver", "setme");
                config.setProperty("url", "setme");
                config.setProperty("user", "setme");
                config.setProperty("password", "setme");
                try(OutputStream out = Files.newOutputStream(configPath)) {
                    config.store(out, "");
                }
            }
            try (InputStream in = Files.newInputStream(configPath)) {
                config.load(in);
            }
        } catch (Exception e) {
            LOGGER.error("Cannot load config", e);
            throw new RuntimeException(e);
        }

    }

    private static String getDriver() {
        return config.getProperty("driver");
    }

    private static String getUrl() {
        return config.getProperty("url");
    }

    private static String getUser() {
        return config.getProperty("user");
    }

    private static String getPassword() {
        return config.getProperty("password");
    }

    private static Connection createConnection() throws Exception {
        final String driver = Objects.requireNonNull(getDriver(), "driver is mandatory");
        final String url = Objects.requireNonNull(getUrl(), "url is mandatory");
        final String user = Objects.requireNonNull(getUser(), "user is mandatory");
        final String password = Objects.requireNonNull(getPassword(), "password is mandatory");
        LOGGER.info("Opening connection: driver={}, url={}, user={}", driver, url, user);
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }

    private static void executeTest(Test test) {
        final LocalTime start = LocalTime.now();
        LOGGER.info("Executing: {}", test.getName());
        try {
            test.test();
            final Duration duration = Duration.between(start, LocalTime.now());
            LOGGER.info("Duration: {}ms", duration.toMillis());
        } catch (Exception e) {
            LOGGER.error("Test aborted", e);
        }

    }

    public static void main(String[] args)  {
        try (Connection connection = createConnection()) {
            executeTest(new TpjwebappsTest(connection, 1));
            executeTest(new TpjwebappsTest(connection, 10));
            executeTest(new TpjwebappsTest(connection, 50));
            executeTest(new TpjwebappsTest(connection, 300));
        } catch(Exception e) {
            LOGGER.error("Error during test executon", e);
            System.exit(1);
        }
    }
}
