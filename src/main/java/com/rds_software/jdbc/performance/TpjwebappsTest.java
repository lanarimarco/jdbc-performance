package com.rds_software.jdbc.performance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TpjwebappsTest implements Test{

    private final Connection connection;
    private final int iterations;

    public TpjwebappsTest(Connection connection, int iterations) {
        this.connection = connection;
        this.iterations = iterations;
    }

    @Override
    public String getName() {
        return "TPJWEBAPPS " + iterations + " iterations";
    }

    public void test() throws Exception{
        for(int iteration = 0; iteration < iterations; iteration++) {
            final String select = "SELECT ID, URL, TARGET, JSOPTIONS, ENV_IND, DESCR FROM TPJWEBAPPS";
            try(PreparedStatement stmt = connection.prepareStatement(select)) {
                try (ResultSet resultSet = stmt.executeQuery()) {
                    while (resultSet.next()) {
                        for(int column = 1; column <= 6; column++) {
                            resultSet.getString(column);
                        }
                    }
                }
            }
        }
    }
}
