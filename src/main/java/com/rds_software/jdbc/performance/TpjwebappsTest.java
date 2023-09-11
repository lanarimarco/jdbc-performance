package com.rds_software.jdbc.performance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TpjwebappsTest implements Test{

    private final Connection connection;

    public TpjwebappsTest(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String getName() {
        return "TPJWEBAPPS 300 iter";
    }

    public void test() throws Exception{
        for(int test = 0; test < 300; test++) {
            final String select = "SELECT ID, URL, TARGET, JSOPTIONS, ENV_IND, DESCR FROM TPJWEBAPPS";
            try(PreparedStatement stmt = connection.prepareStatement(select)) {
                try (ResultSet resultSet = stmt.executeQuery()) {
                    while (resultSet.next()) {
                        for(int i = 1; i <= 6; i++) {
                            resultSet.getString(i);
                        }
                    }
                }
            }
        }
    }
}
