package org.apache.calcite.examples.foodmart.java;

import java.sql.*;

/**
 * Created by haotan on 3/11/17.
 */
public class MyJdbcTest {
    public static void main(String[] args) throws Exception {
        new MyJdbcTest().run();
    }

    public void run() throws ClassNotFoundException, SQLException {

        //String url1 = "jdbc:calcite:model=/Users/haotan/Desktop/Projects/calcite/example/csv/target/test-classes/tpch.json";
        String url1 = "jdbc:calcite:model=/Users/haotan/Desktop/Projects/calcite/models/tpch.json";
        //String url2= "jdbc:calcite:schemaType=JDBC;schema.jdbcUser=root;schema.jdbcUrl=jdbc:mysql://localhost:3306/tpch";

        Connection connection =
                DriverManager.getConnection(url1);
        Statement statement = connection.createStatement();
        ResultSet resultSet =
                statement.executeQuery("select o_shippriority from customer, orders where c_custkey = o_custkey and C_CUSTKEY < 100");
        final StringBuilder buf = new StringBuilder();
        while (resultSet.next()) {
            int n = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= n; i++) {
                buf.append(i > 1 ? "; " : "")
                        .append(resultSet.getMetaData().getColumnLabel(i))
                        .append("=")
                        .append(resultSet.getObject(i));
            }
            System.out.println(buf.toString());
            buf.setLength(0);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
