package cn.com.gs.ssm.libraryMIS.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBTest {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/db_mis?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT";
        String username = "root";
        String password = "root";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        if (!connection.isClosed())
            System.out.println(String.format("connection to %s successfully.", url));

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from gs_user where account = 'admin'");
        while (resultSet.next()) {
            int rowId = resultSet.getInt(1); //注意这里，JDBC中的ColumnIndex是从1开始的。
            System.out.println("id:" + rowId);
        }
    }
}
