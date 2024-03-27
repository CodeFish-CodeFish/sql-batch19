import org.junit.Test;

import java.sql.*;

public class JDBCIntro {


    @Test
    public void connectionTest() throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:oracle:thin:@database-1.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                        "student","codefish385");
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");

//        resultSet.next();
//        System.out.println(resultSet.getString(1));
//        System.out.println(resultSet.getString(2));

        while (resultSet.next()){
            System.out.print(resultSet.getString(1) + " ");
            System.out.print(resultSet.getString(2) + " ");
            System.out.print(resultSet.getString(3) + " ");
            System.out.print(resultSet.getString(4) + " ");
            System.out.print(resultSet.getString(5) + " ");
            System.out.println();
        }


        connection.close();
        statement.close();
        resultSet.close();
    }

    @Test
    public void queryTest() throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:oracle:thin:@database-1.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                        "student","codefish385");
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        System.out.println(columnCount);
//        resultSet.next();


        while (resultSet.next()){
            for (int i = 1; i<=columnCount; i++){
                System.out.print("| " + resultSet.getObject(i));
            }
            System.out.println();
        }


    }

}
