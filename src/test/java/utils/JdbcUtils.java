package utils;

import org.junit.After;
import org.junit.AfterClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {

    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;


    private static Statement establishConnection(){
        try{
            connection = DriverManager.getConnection(getProp("connection_string")
                    ,getProp("user_name")
                    ,getProp("password"));

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        }catch (SQLException sqlException){
            throw new RuntimeException("Could not connect to database.");
        }
        return statement;
    }

    public static ResultSet queryDB(String query){
        statement = establishConnection();
        try {
            resultSet = statement.executeQuery(query);
            return resultSet;
        }catch (SQLException sqlException){
            throw new RuntimeException("Failed running query.");
        }
    }

    private static String getProp(String key){
        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream(new File("/Users/codefish/Downloads/Project/Project/SQLBATCH19/src/test/resources/database.properties")));
        }catch (IOException exception){
            throw new RuntimeException("Could not find properties file.");
        }

        return properties.getProperty(key);
    }

    @After
    public void closeResources(){
        System.out.println("connection closed");
        try {
            if (resultSet!=null){
                resultSet.close();
            }
            if (statement!=null){
                statement.close();
            }
            if (connection!=null){
                connection.close();
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }

    }


}
